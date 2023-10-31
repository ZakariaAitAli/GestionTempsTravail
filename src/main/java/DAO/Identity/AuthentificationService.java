package DAO.Identity;

import DAO.Shared.Driver;
import Interfaces.Services.IAuthentificationService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthentificationService  implements IAuthentificationService {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultat = null;

    public AuthentificationService() {}
    public boolean login(String uemail, String upassword) throws Exception {
        if (conn == null) {conn = Driver.driver();}
        statement = conn.prepareStatement("select * from employees where email=? and password=?");
        statement.setString(1, uemail);
        statement.setString(2, upassword);
        resultat = statement.executeQuery();
        if(resultat.next()) {
            return true;
        }
        return false ;
    }
}
