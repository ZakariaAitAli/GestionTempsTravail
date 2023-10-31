package DAO.Shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

    public static Connection driver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt", "root", "");
        } catch (ClassNotFoundException ex) {
            throw new Exception("impossible de charger le driver");
        } catch (SQLException e) {
            throw new Exception("erreur" + e.getMessage());
        }
    }
}
