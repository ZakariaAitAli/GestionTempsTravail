package DAO.Environment;

import DAO.Shared.Driver;
import Interfaces.Services.IMoodService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MoodService implements IMoodService {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Integer> getAllHumeurs() throws Exception {
        if(conn ==null) {conn = Driver.driver();}
        List<Integer> humeurs = new ArrayList<>();
        String query = "SELECT * FROM humeurs";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            humeurs.add(resultSet.getInt("humeur"));
        }
        return humeurs;
    }

    public void insertMood(int humeur) throws Exception {
        if(conn ==null) {conn = Driver.driver();}
        stmt = conn.prepareStatement("INSERT INTO humeurs (humeur) VALUES (?)");
        stmt.setInt(1, humeur);
        stmt.executeUpdate();
    }

    public void MoodSubmited(int idEmployee)throws Exception {
        if(conn ==null) {conn = Driver.driver();}
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        stmt = conn.prepareStatement("UPDATE  times SET mood_form = ? WHERE id_employee =? AND date =?");
        stmt.setBoolean(1, true);
        stmt.setInt(2, idEmployee);
        stmt.setDate(3,sqlDate);
        stmt.executeUpdate();

    }

    public boolean CheckMood(int idEmployee) throws Exception {
        if(conn ==null) {conn = Driver.driver();}
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        stmt = conn.prepareStatement("SELECT t.mood_form FROM times t WHERE t.id_employee =? AND t.date = ?");

        stmt.setInt(1, idEmployee);
        stmt.setDate(2,sqlDate);
        rs = stmt.executeQuery();
        if(rs.next()) {
            return rs.getBoolean("mood_form") ;
        }

        return false ;
    }
}