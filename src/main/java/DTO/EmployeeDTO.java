package DTO;

import java.util.Date;

public class EmployeeDTO {

    public double hoursWorkedAfterPause ;
    public double hoursSupp;
    public Date date ;


    public EmployeeDTO( double HoursSupp , double HoursWorkedAfterPause,Date date) {


       this.hoursSupp = HoursSupp;

        this.hoursWorkedAfterPause = HoursWorkedAfterPause;
        this.date = date ;
    }


}
