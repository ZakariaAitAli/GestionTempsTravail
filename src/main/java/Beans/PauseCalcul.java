package Beans;
import DAO.Pause;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PauseCalcul {



    public List<Pause> getAllPauses() {
        List<Pause> pauses = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtt-2", "root", "");


            String query = "SELECT * FROM pauses";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pause pause = new Pause();
                pause.setId_pause(resultSet.getInt("id_pause"));
                pause.setPause(resultSet.getInt("pause"));
                pauses.add(pause);
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
                  return pauses;
            }
        }


