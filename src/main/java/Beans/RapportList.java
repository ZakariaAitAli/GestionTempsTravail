package Beans;

import DAO.Rapport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RapportList {
    public static List<Rapport> getRapportsByEmployeeId(int employeeId) {
        List<Rapport> rapports = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt-2","root","");


            String sql = "SELECT * FROM rapports WHERE id_employee = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Rapport rapport = new Rapport();
                rapport.setId_rapport(rs.getInt("id_rapport"));
                rapport.setId_employee(rs.getInt("id_employee"));
                rapport.setPath(rs.getString("path"));
                rapport.setDate_generation(rs.getString("date_generation"));
                rapport.setWorktime_total(rs.getInt("worktime_total"));
                System.out.println("hello" + rapport.getPath());
                rapports.add(rapport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rapports;
    }
}
