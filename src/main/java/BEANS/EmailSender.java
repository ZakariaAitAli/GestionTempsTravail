package BEANS;

import java.sql.*;

public class EmailSender {

    static final String DB_URL = "jdbc:mysql://localhost:3306/GTT";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    public static void main(String[] args) throws SQLException {

        CronJobScriptEmail cronjobscriptemail = new CronJobScriptEmail(); // Instanciez la classe
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Exécuter une requête pour obtenir les employés
        Statement stmt = conn.createStatement();
        ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM employees");

        while (rs.next()) {
            String email = rs.getString("email");
            cronjobscriptemail.sendEmail(email);
        }

        // Fermer les ressources
        rs.close();
        stmt.close();
        conn.close();
    }
}