package DAO.Shared;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

    public static Connection driver() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt", "root", "");
        }catch (SQLException e) {
            throw new Exception("erreur" + e.getMessage());
        }
    }
}
