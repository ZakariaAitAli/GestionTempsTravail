package Interfaces.Services;

import DTO.ReportingDTO;

import java.util.ArrayList;

public interface IReportingService {
    void insertReport(int idEmployee) throws Exception;
    ArrayList<ReportingDTO> getReport(int idEmployee) throws Exception;
}
