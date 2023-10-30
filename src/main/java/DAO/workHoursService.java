package DAO;
import java.sql.*;

public class workHoursService {

    Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;
    public workHoursService() { }
    public void driver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt", "root", "");
            System.out.println("Connexion reussite ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
