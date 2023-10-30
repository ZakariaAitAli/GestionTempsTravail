package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class employeeList {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établir une connexion à la base de données (assurez-vous d'avoir les paramètres de connexion appropriés)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt-2","root","");

            // Requête SQL pour récupérer les employés
            String sql = "SELECT * FROM employees";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Parcourir les résultats et créer des objets Employee
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId_employee(rs.getInt("id_employee")); // Ajout de l'ID de l'employé
                employee.setF_name(rs.getString("f_name"));
                employee.setL_name(rs.getString("l_name"));
                employee.setEmail(rs.getString("email"));
                System.out.println("hello" + employee.getF_name());
                employees.add(employee);
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

        return employees;
    }

}
