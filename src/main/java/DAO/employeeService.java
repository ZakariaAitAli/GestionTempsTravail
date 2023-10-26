package DAO;

import Models.Employee;

import java.sql.*;
import java.util.ArrayList;

public class employeeService {
    Connection conn = null;

    ResultSet resultat = null;
    public employeeService() { }
    public void driver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt", "root", "");
            System.out.println("Connexion reussite ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public ArrayList<Employee> GetEmployeeData() throws SQLException {
        driver();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT t.*, f_name, l_name,email FROM employees e INNER JOIN time t ON e.id_employee == t.id_employee  ");
        ResultSet resultat = preparedStatement.executeQuery();
        while (resultat.next()) {

           String FullName = resultat.getString("f_name") +" " +resultat.getString("l_name");
           String email = resultat.getString("email") ;



        }

    }*/
}
