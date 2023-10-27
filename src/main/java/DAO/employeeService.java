package DAO;


import DTO.EmployeeDTO;
import Models.Employee;
import Shared.Enums.PausesEnum;

import java.io.PrintWriter;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

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
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt", "root", "");
        } catch (ClassNotFoundException ex) {
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




    public ArrayList<EmployeeDTO> GetEmployeeData(String email) throws Exception {

        if (conn == null) {
            conn = driver();
        }


            LocalDateTime currentDateTime = LocalDateTime.now();
            java.util.Date javaUtilDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());
            ArrayList<EmployeeDTO> data = new ArrayList<>() ;

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT e.f_name, e.l_name,e.email,t.*,p.pause FROM employees e INNER JOIN time t ON e.id_employee = t.id_employee INNER JOIN pauses p ON p.id_time = t.id where e.email =? AND t.date >=?");
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2, sqlDate);
            ResultSet resultat = preparedStatement.executeQuery();

            while (resultat.next()) {

                String FullName = resultat.getString("f_name") + " " + resultat.getString("l_name");
                Time startTime = resultat.getTime("start_time");
                Time endTime = resultat.getTime("end_time");
                int pause = PausesEnum.getHours(resultat.getInt("pause")) ;
                long totalMillisecondsWorked = endTime.getTime() - startTime.getTime();
                double totalHoursWorked = totalMillisecondsWorked / (1000.0 * 60.0 );
                double hoursWorkedAfterPause = totalHoursWorked - pause;

                data.add(new EmployeeDTO( startTime, endTime,pause ,hoursWorkedAfterPause));
            }
        return data;
    }

    public ArrayList<String> GetAllEmails() throws Exception {
        conn  = driver();
        ArrayList<String> emails = new ArrayList<>() ;

        PreparedStatement preparedStatement = conn.prepareStatement("Select email from employees ;");
        ResultSet resultat = preparedStatement.executeQuery();

        while (resultat.next()) {
            String email = resultat.getString("email");
            emails.add(email) ;
        }
        return emails ;
     }

     public HashMap<String , ArrayList<EmployeeDTO>> GetAll() throws Exception {
         HashMap<String , ArrayList<EmployeeDTO>> data = new HashMap<>() ;
         ArrayList<String> emails = GetAllEmails() ;

         for (String item: emails) {
            ArrayList<EmployeeDTO> EmployeData =  GetEmployeeData(item) ;
            data.put(item ,EmployeData);
         }

         return  data ;
     }
}

