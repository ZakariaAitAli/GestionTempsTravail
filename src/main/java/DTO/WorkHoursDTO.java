package DTO;

import java.sql.Time;

public class WorkHoursDTO {

    public int idEmployee ;
    public Time startTime;

    public Time endTime ;
    //public String[] pause ;

    public WorkHoursDTO(int idEmployee, Time startTime, Time endTime) {
        this.idEmployee = idEmployee;
        this.startTime = startTime;
        this.endTime = endTime;

    }





}
