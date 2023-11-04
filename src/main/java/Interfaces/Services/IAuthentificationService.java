package Interfaces.Services;

import java.sql.SQLException;

public interface IAuthentificationService {

    boolean login(String uemail, String upassword) throws Exception;

    boolean isAdmin(String uemail, String upassword) throws Exception;
}
