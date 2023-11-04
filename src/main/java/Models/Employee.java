package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    public int id ;
    public String firstName ;
    public String LastName ;
    public double hoursWorked ;
    public double hoursSupp ;

    public Employee() {

    }
    public Employee(String firstName, String lastName, double hoursWorked, double hoursSupp) {
        this.firstName = firstName;
        LastName = lastName;
        this.hoursWorked = hoursWorked;
        this.hoursSupp = hoursSupp;
    }
}
