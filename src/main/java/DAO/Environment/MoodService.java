package DAO.Environment;

import DAO.Shared.Driver;
import Interfaces.Services.IMoodService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoodService implements IMoodService {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Integer> getAllHumeurs() throws Exception {
        if(conn ==null) {conn = Driver.driver();}
           List<Integer> humeurs = new ArrayList<>();
            String query = "SELECT * FROM humeurs";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                humeurs.add(resultSet.getInt("humeur"));
            }
        return humeurs;
    }
}
