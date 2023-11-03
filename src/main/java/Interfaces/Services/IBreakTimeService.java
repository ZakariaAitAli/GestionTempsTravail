package Interfaces.Services;

import DTO.BreakDTO;

import java.util.List;

public interface IBreakTimeService {
    List<Integer> getAllPauses() throws Exception ;
    void insertBreakTime(BreakDTO breakDTO) throws Exception;
}
