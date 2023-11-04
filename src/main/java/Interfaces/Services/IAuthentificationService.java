package Interfaces.Services;

public interface IAuthentificationService {

    boolean login(String uemail, String upassword) throws Exception;
}
