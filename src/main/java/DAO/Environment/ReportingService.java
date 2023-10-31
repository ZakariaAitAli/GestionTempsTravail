package DAO.Environment;

import DAO.Shared.Driver;
import Interfaces.Services.IReportingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportingService implements IReportingService {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;

    public ReportingService() {
    }

    public void insertReport(int idEmployee) throws Exception{

        if (conn == null) {conn = Driver.driver();}
        statement = conn.prepareStatement("INSERT INTO reports (id_employee) VALUES (?)");
        statement.setInt(1, idEmployee);
        statement.executeUpdate();


    }


    }
