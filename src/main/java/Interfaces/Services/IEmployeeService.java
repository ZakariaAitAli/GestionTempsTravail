package Interfaces.Services;

import DTO.EmployeeDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface IEmployeeService {



    ArrayList<EmployeeDTO> GetEmployeeData(String email) throws Exception;
    ArrayList<String> GetAllEmails() throws Exception;
    HashMap<String , ArrayList<EmployeeDTO>> GetAll() throws Exception;
    int getId(String email)throws Exception;
}
