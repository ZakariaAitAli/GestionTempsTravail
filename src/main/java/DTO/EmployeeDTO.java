package DTO;

import java.util.Date;

public class EmployeeDTO {



    public Date startDate ;
    public Date endDate ;
    public int pause ;
    public double hoursWorkedAfterPause ;


    public EmployeeDTO(  double hoursWorkedAfterPause) {

      /* this.startDate = startDate;
        this.endDate = endDate;
        this.pause = pause ;*/


        this.hoursWorkedAfterPause = hoursWorkedAfterPause;
    }


}
