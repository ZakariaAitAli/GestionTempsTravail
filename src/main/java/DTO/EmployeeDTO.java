package DTO;

import java.util.Date;

public class EmployeeDTO {

    public double hoursWorkedAfterPause ;
    public double hoursSupp;
    public Date date ;
    public int pause ;


    public EmployeeDTO( double HoursSupp , double HoursWorkedAfterPause,Date date,int pause) {


       this.hoursSupp = HoursSupp;

        this.hoursWorkedAfterPause = HoursWorkedAfterPause;
        this.date = date ;
        this.pause = pause ;
    }


}
