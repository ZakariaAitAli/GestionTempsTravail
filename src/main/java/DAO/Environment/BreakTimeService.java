package DAO.Environment;

import DAO.Shared.Driver;
import Interfaces.Services.IBreakTimeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreakTimeService  implements IBreakTimeService {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    public List<Integer> getAllPauses() throws Exception{
        if(conn ==null) {conn =Driver.driver();}

            List<Integer> pauses = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM pauses");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pauses.add(resultSet.getInt("pause"));
            }

        return pauses;
    }
}
