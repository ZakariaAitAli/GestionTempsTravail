package DAO.Environment;

import DAO.Shared.Driver;
import DTO.ReportingDTO;
import Interfaces.Services.IReportingService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ReportingService implements IReportingService {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;

    public ReportingService() {
    }

    public void insertReport(int idEmployee) throws Exception{

        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date javaUtilDate = java.util.Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(javaUtilDate.getTime());

        if (conn == null) {conn = Driver.driver();}
        statement = conn.prepareStatement("INSERT INTO reports (Date, id_employee) VALUES (?,?)");
        statement.setInt(1, idEmployee);
        statement.setDate(2, sqlDate);

        statement.executeUpdate();
    }
    public ArrayList<ReportingDTO> getReport(int idEmployee) throws Exception {
        if (conn == null) {conn = Driver.driver();}
        ArrayList<ReportingDTO> reportsData = new ArrayList<>() ;
        statement = conn.prepareStatement("SELECT r.Date, r.Id FROM reports r INNER JOIN employees emp ON r.id_employee = emp.id_employee WHERE r.id_employee =?");
        statement.setInt(1, idEmployee);
        resultat =   statement.executeQuery();
        while (resultat.next()) {
            Date date = resultat.getDate("Date") ;
            int Id = resultat.getInt("Id") ;

            reportsData.add(new ReportingDTO(Id,date)) ;
        }

        return reportsData ;
    }



    }
