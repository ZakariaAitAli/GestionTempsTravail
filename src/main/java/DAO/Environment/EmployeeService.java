package DAO.Environment;
import DAO.Shared.Driver;
import DTO.EmployeeDTO;
import Interfaces.Services.IEmployeeService;
import Shared.Enums.PausesEnum;
import java.time.LocalDate;
import java.util.Date;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class EmployeeService implements IEmployeeService {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;

    public EmployeeService() {
    }

    public ArrayList<EmployeeDTO> GetEmployeeData(String email) throws Exception {
        conn = Driver.driver();
        if (conn == null) {conn = Driver.driver();}
             //DATE CALC :
            LocalDateTime currentDateTime = LocalDateTime.now();
            java.util.Date javaUtilDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());
            LocalDate localDate = sqlDate.toLocalDate();
            LocalDate sevenDaysAgoLocal = localDate.minusDays(7);
            java.sql.Date sevenDaysAgo= java.sql.Date.valueOf(sevenDaysAgoLocal);

            ArrayList<EmployeeDTO> data = new ArrayList<>() ;

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT e.f_name, e.l_name,e.email,t.* FROM employees e INNER JOIN time t ON e.id_employee = t.id_employee where e.email =? AND  t.date <= ? AND t.date >=?");
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setDate(3,sevenDaysAgo);


            ResultSet resultat = preparedStatement.executeQuery() ;
            int  pause = 0 ;
            while (resultat.next()) {
                 pause = 0 ;
                String FullName = resultat.getString("f_name") + " " + resultat.getString("l_name");
                Time startTime = resultat.getTime("start_time");
                Time endTime = resultat.getTime("end_time");
                Date date = resultat.getDate("date") ;
                int idTime = resultat.getInt("id") ;

                PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT pause FROM pauses WHERE id_time = ?");
                preparedStatement2.setInt(1, idTime);
                ResultSet res = preparedStatement2.executeQuery();
                while (res.next()) {
                     pause += PausesEnum.getHours(res.getInt("pause")) ;
                }
                if(endTime ==null) { endTime = Time.valueOf("17:00:00");}
                long totalMillisecondsWorked = endTime.getTime() - startTime.getTime();
                double totalMinutesWorked = totalMillisecondsWorked / (1000.0 * 60.0 );
                double HoursWorked  = totalMinutesWorked /60;
                double HoursWorkedAfterPause = (totalMinutesWorked - pause )/60;
                double HoursSupp = (HoursWorked -8)>= 0 ? (HoursWorked -8) : 0; // 7 HOURS EACH DAY

                data.add(new EmployeeDTO(HoursSupp,HoursWorkedAfterPause,date,pause));
            }
        return data;
    }

    public ArrayList<String> GetAllEmails() throws Exception {
        conn =Driver.driver();

        if (conn == null) {conn = Driver.driver();}
        ArrayList<String> emails = new ArrayList<>() ;

        PreparedStatement preparedStatement = conn.prepareStatement("Select * from employees ;");
        ResultSet resultat = preparedStatement.executeQuery();

        while (resultat.next()) {
            String email = resultat.getString("email");
            String FullName = resultat.getString("f_name") + " " + resultat.getString("l_name");
            int idEmployee= resultat.getInt("id_employee") ;
            emails.add(email+"/"+FullName+"/"+idEmployee) ;
        }
        return emails ;
     }

     public HashMap<String , ArrayList<EmployeeDTO>> GetAll() throws Exception {
         HashMap<String , ArrayList<EmployeeDTO>> data = new HashMap<>() ;
         ArrayList<String> emails = GetAllEmails() ;

         for (String item: emails) {
             String email  = item.split("/")[0] ;
             ArrayList<EmployeeDTO> EmployeData =  GetEmployeeData(email) ;
             data.put(item ,EmployeData);
         }
         return  data ;
     }

    public int getId(String email)throws Exception {
        if (conn == null) {conn = Driver.driver();}
        PreparedStatement preparedStatement = conn.prepareStatement("Select id_employee from employees WHERE email =? ;");
        preparedStatement.setString(1, email);
        ResultSet resultat = preparedStatement.executeQuery();

        if (resultat.next()) {
             int idEmployee = resultat.getInt("id_employee") ;
             return idEmployee ;
        }
        return 0;
    }
}

