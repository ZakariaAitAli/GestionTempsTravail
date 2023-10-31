package DTO;

import java.util.Date;

public class ReportingDTO {


    public int id;
    public Date ReportDate;


    public ReportingDTO(int id , Date date) {

        this.ReportDate = date;
        this.id = id ;
    }
}
