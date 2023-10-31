package DAO;


import DTO.EmployeeDTO;
import Models.Employee;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class employeeService {
    String email;
    String password;
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;
    public employeeService() { }
    public Connection driver() throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");

   /* public ArrayList<Employee> GetEmployeeData() throws SQLException {
        driver();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT t.*, f_name, l_name,email FROM employees e INNER JOIN time t ON e.id_employee == t.id_employee  ");
        ResultSet resultat = preparedStatement.executeQuery();
        while (resultat.next()) {

           String FullName = resultat.getString("f_name") +" " +resultat.getString("l_name");
           String email = resultat.getString("email") ;



        }

    }*/

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt","root","");
        }catch(ClassNotFoundException ex ){
            throw new Exception("impossible de charger le driver");
        } catch (SQLException e) {
            throw new Exception("erreur" + e.getMessage());
        }
    }

    public boolean login(String uemail, String upassword) throws Exception {

        if (conn == null) {
            conn = driver();
        }

        statement = conn.prepareStatement("select * from employees where email=? and password=?");
        statement.setString(1, uemail);
        statement.setString(2, upassword);
        resultat = statement.executeQuery();
        if (resultat.next()) {
            System.out.println(resultat.getString("id_employee"));
            // response.sendRedirect("Servlets.HomeServlet");
            return true;
        } else
            return false;
    }

        public void insertTime(Date start_time, Date end_date, int pause) throws Exception {
            if (conn == null){
                conn = driver();
            }

        }
    }

