package Interfaces.Services;

import DTO.WorkHoursDTO;

public interface IWorkHoursService {

    boolean insertTime(WorkHoursDTO object) throws Exception;
    boolean checkStartWork (int idEmployee) throws Exception;
    boolean checkEndWork (int idEmployee) throws Exception;

}
