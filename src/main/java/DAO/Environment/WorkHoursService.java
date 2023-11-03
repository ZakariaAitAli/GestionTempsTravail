package DAO.Environment;
import DAO.Shared.Driver;
import DTO.BreakDTO;
import DTO.WorkHoursDTO;
import Interfaces.Services.IWorkHoursService;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ExecutionException;

public class WorkHoursService implements IWorkHoursService {

    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;
    public WorkHoursService() { }

    public String insertTime(WorkHoursDTO object) throws Exception {

        if (conn == null) {conn = Driver.driver();}

        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());
        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT count(*) FROM time WHERE date = ? AND id_employee =?");
        preparedStatement2.setDate(1, sqlDate);
        preparedStatement2.setInt(1, object.idEmployee);
        ResultSet resultat = preparedStatement2.executeQuery();
        if (resultat.next()) {
            return "the work hours of the day has already been added.";
        } else {
            if(object.startTime != null) {
                statement = conn.prepareStatement("INSERT INTO time(start_time,id_employee) VALUES(?,?)"); //Statement.RETURN_GENERATED_KEYS);
                statement.setTime(1, object.startTime);
                statement.setInt(2, object.idEmployee);
                statement.executeUpdate();
            }else if(object.endTime != null) {
                statement = conn.prepareStatement("INSERT INTO time(end_time,id_employee) VALUES(?,?)");
                statement.setTime(1, object.endTime);
                statement.setInt(2, object.idEmployee);
                statement.executeUpdate();
            }
            return "error";

           /* ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int lastInsertedId = generatedKeys.getInt(1);
                String[] pauses = object.pause;
                for (String pause : pauses) {
                    statement = conn.prepareStatement("INSERT INTO pauses(id_time,pause) VALUES(?,?)");
                    statement.setInt(1, lastInsertedId);
                    statement.setString(2, pause);
                    statement.executeUpdate();
                }
                return "Success";
            }*/
        }

    }



    public boolean checkStartWork (int idEmployee) throws Exception {

        if (conn == null) {
            conn = Driver.driver();
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT count(*) FROM time WHERE date = ? and id_employee =?");
        preparedStatement2.setDate(1, sqlDate);
        preparedStatement2.setInt(2,idEmployee);
        ResultSet resultat = preparedStatement2.executeQuery();
        if(resultat.next()) {
            return true ;
        }
        return false ;
    }

    public boolean checkEndWork (int idEmployee) throws Exception {
        if (conn == null) {
            conn = Driver.driver();
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT end_time FROM time WHERE date = ? and id_employee =? ");
        preparedStatement2.setDate(1, sqlDate);
        preparedStatement2.setInt(2, idEmployee);
        ResultSet resultat = preparedStatement2.executeQuery();
        while(resultat.next()) {
            Time end_time = resultat.getTime("end_time");
            if(end_time == null) {return false;} else{return true;}
        }
        return false ;
    }



}
