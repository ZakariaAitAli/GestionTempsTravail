package DAO.Environment;
import DAO.Shared.Driver;
import DTO.WorkHoursDTO;
import Interfaces.Services.IWorkHoursService;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
            statement = conn.prepareStatement("INSERT INTO time(start_time,end_time,id_employee) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setTime(1, object.startTime);
            statement.setTime(2, object.endTime);
            statement.setInt(3, object.idEmployee);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

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
            }
            return "Error";
        }

    }

}
