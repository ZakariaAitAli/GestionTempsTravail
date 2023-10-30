package Beans;

import DAO.Humeur;
import DAO.Pause;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HumeurCalcul {

    public List<Humeur> getAllHumeurs() {
        List<Humeur> humeurs = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt-2", "root", "");


            String query = "SELECT * FROM humeurs";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Humeur humeur = new Humeur();
                humeur.setId_humeur(resultSet.getInt("id_humeur"));
                humeur.setHumeur(resultSet.getInt("humeur"));
                humeurs.add(humeur);
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
        return humeurs;
    }
}


