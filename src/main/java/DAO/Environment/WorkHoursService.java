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

    public boolean insertTime(WorkHoursDTO object) throws Exception {

        if (conn == null) {conn = Driver.driver();}

        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        try {
            if(object.startTime != null) {
                statement = conn.prepareStatement("INSERT INTO times (date, start_time,id_employee) VALUES(?,?,?)"); //Statement.RETURN_GENERATED_KEYS);
                statement.setDate(1, sqlDate);
                statement.setTime(2, object.startTime);
                statement.setInt(3, object.idEmployee);
                statement.executeUpdate();
            }else if(object.endTime != null) {
                statement = conn.prepareStatement("UPDATE times SET end_time =? where  id_employee=? AND date=?");
                statement.setTime(1, object.endTime);
                statement.setInt(2, object.idEmployee);
                statement.setDate(3, sqlDate);
                statement.executeUpdate();
            }
        } catch(Exception e) {
            return  false ;
        }
        return true;
    }



    public boolean checkStartWork (int idEmployee) throws Exception {

        if (conn == null) {
            conn = Driver.driver();
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM times WHERE date = ? and id_employee =?");
        preparedStatement2.setDate(1, sqlDate);
        preparedStatement2.setInt(2,idEmployee);
         resultat = preparedStatement2.executeQuery();
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

        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT end_time FROM times WHERE date = ? and id_employee =? ");
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
