package DAO;

public class Employee {

    private int id_employee;
    private String f_name;
    private String l_name;
    private String email;



    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    // Getter pour f_name
    public String getF_name() {
        return f_name;
    }

    // Setter pour f_name
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    // Getter pour l_name
    public String getL_name() {
        return l_name;
    }

    // Setter pour l_name
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    // Getter pour email
    public String getEmail() {
        return email;
    }

    // Setter pour email
    public void setEmail(String email) {
        this.email = email;
    }
}



