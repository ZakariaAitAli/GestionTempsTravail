package DAO;

import java.io.PrintWriter;
import java.sql.*;

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
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt","root","");
        }catch(ClassNotFoundException ex ){
            throw new Exception("impossible de charger le driver");
        }catch (SQLException e){
            throw new Exception("erreur"+e.getMessage());
        }
    }
    public  boolean login( String uemail, String upassword) throws Exception {

        if (conn == null){
            conn = driver();
        }

        statement=conn.prepareStatement("select * from employees where email=? and password=?");
        statement.setString(1,uemail);
        statement.setString(2,upassword);
        resultat = statement.executeQuery();
        if(resultat.next()){
            System.out.println(resultat.getString("id_employee"));
            // response.sendRedirect("Servlets.HomeServlet");
            return true;
        }
        else
            return false;
        }

        public void insertTime(Date start_time, Date end_date, int pause) throws Exception {
            if (conn == null){
                conn = driver();
            }

        }
    }

