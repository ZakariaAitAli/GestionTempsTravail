package Servlets;

import DAO.Environment.WorkHoursService;
import DTO.WorkHoursDTO;
import Interfaces.Services.IWorkHoursService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;

@WebServlet(name = "Servlets.workTimeServlet", value = "/Servlets.workTimeServlet")
public class workTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //GETTING POST REQUEST VALUES
        String start_time =(String)request.getParameter("start_time")+":00";
        String end_time = (String)request.getParameter("end_time")+":00";
        String[] pauses = request.getParameterValues("pause");

        HttpSession session=request.getSession();
        int id_employee = (int) session.getAttribute("idEmployee");

        //CALLING SERVICES
        WorkHoursDTO workHoursDTO = new WorkHoursDTO(id_employee,Time.valueOf(start_time),Time.valueOf(end_time),pauses) ;
        IWorkHoursService _workHoursService = new WorkHoursService();
        try { String message = _workHoursService.insertTime(workHoursDTO);} catch (Exception e) {throw new RuntimeException(e);}







    }
}