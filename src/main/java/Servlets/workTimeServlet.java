package Servlets;

import DAO.Environment.BreakTimeService;
import DAO.Environment.WorkHoursService;
import DTO.BreakDTO;
import DTO.WorkHoursDTO;
import Interfaces.Services.IBreakTimeService;
import Interfaces.Services.IWorkHoursService;
import Shared.Enums.PausesEnum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;

import static java.lang.Integer.parseInt;

@WebServlet(name = "Servlets.workTimeServlet", value = "/Servlets.workTimeServlet")
public class workTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id_employee = (int) session.getAttribute("idEmployee");

        if(id_employee !=0) {
            IWorkHoursService _workHoursService = new WorkHoursService();
            try {
               boolean isStart =  _workHoursService.checkEndWork(id_employee);
               boolean isEnd   =  _workHoursService.checkStartWork(id_employee);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //SESSION :
        HttpSession session=request.getSession();
        int id_employee = (int) session.getAttribute("idEmployee");

        //GETTING POST REQUEST VALUES
        String start_time =(String)request.getParameter("start_time")+":00";
        String end_time = (String)request.getParameter("end_time")+":00";
        //int pause = parseInt(request.getParameter("pause") );
        //String[] pauses = request.getParameterValues("pause");
        int pause = 1;

        //CALLING WorkHours SERVICES
        WorkHoursDTO workHoursDTO = new WorkHoursDTO(id_employee,Time.valueOf(start_time),Time.valueOf(end_time)) ;
        IWorkHoursService _workHoursService = new WorkHoursService();
        try { String message = _workHoursService.insertTime(workHoursDTO);} catch (Exception e) {throw new RuntimeException(e);}

       //CALLING THE BREAKTIME SERVICE :
        int pauseMin =  PausesEnum.getHours(pause) ;
        BreakDTO breakDTO = new BreakDTO(id_employee,pauseMin) ;
        IBreakTimeService _breakTimeService = new BreakTimeService();
        try {_breakTimeService.insertBreakTime(breakDTO);} catch (Exception e) {throw new RuntimeException(e);}


    }
}