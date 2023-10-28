package DTO;

import java.util.Date;

public class EmployeeDTO {

    public double hoursWorkedAfterPause ;
    public double hoursSupp;


    public EmployeeDTO( double HoursSupp , double HoursWorkedAfterPause) {


       this.hoursSupp = HoursSupp;

        this.hoursWorkedAfterPause = HoursWorkedAfterPause;
    }


}
