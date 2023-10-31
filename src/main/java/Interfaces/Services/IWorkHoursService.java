package Interfaces.Services;

import DTO.WorkHoursDTO;

public interface IWorkHoursService {
    void driver() ;
    String insertTime(WorkHoursDTO object) throws Exception;

}
