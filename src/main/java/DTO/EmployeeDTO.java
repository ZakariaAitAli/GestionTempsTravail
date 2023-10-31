package DTO;

import java.util.Date;

public class EmployeeDTO {


    public String fullName ;
    public Date startDate ;
    public Date endDate ;


    public EmployeeDTO(String fullName, Date startDate, Date endDate) {
        this.fullName = fullName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
