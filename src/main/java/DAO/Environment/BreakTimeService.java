package DAO.Environment;

import DAO.Shared.Driver;
import DTO.BreakDTO;
import Interfaces.Services.IBreakTimeService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BreakTimeService  implements IBreakTimeService {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    public List<Integer> getAllPauses() throws Exception{
        if(conn ==null) {conn =Driver.driver();}

            List<Integer> pauses = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM pauses");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pauses.add(resultSet.getInt("pause"));
            }

        return pauses;
    }

    public void insertBreakTime(BreakDTO breakDTO) throws Exception {
        if (conn == null) {
            conn = Driver.driver();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());
        PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT id FROM time WHERE date=? and id_employee = ?");
        preparedStatement2.setDate(1, sqlDate);
        preparedStatement2.setInt(2, breakDTO.idEmployee);
        ResultSet resultat = preparedStatement2.executeQuery();
        if (resultat.next()) {
            stmt = conn.prepareStatement("INSERT INTO pauses(id_time,pause) VALUES(?,?)");
            stmt.setInt(1, resultat.getInt("id_time"));
            stmt.setInt(2, breakDTO.pause);
            stmt.executeUpdate();
        }
    }
}
