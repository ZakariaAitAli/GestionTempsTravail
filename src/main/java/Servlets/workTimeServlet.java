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
        int id_employee = 1;

        //GETTING POST REQUEST VALUES
        Time startTime = request.getParameter("start_time").isEmpty()? null: Time.valueOf(request.getParameter("start_time")+":00") ;
       // String end_time = (String)request.getParameter("end_time")+":00";
        Time endTime = request.getParameter("end_time").isEmpty()? null: Time.valueOf(request.getParameter("end_time")+":00") ;
        //int pause = parseInt(request.getParameter("pause") );
        //String[] pauses = request.getParameterValues("pause");
        int pause = 1;

        //CALLING WorkHours SERVICES
        WorkHoursDTO workHoursDTO = new WorkHoursDTO(id_employee,startTime,endTime) ;
        IWorkHoursService _workHoursService = new WorkHoursService();
        try {
            boolean succeded = _workHoursService.insertTime(workHoursDTO);
            if(succeded) {
                response.setStatus(HttpServletResponse.SC_OK);
            }else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }


        } catch (Exception e) {throw new RuntimeException(e);}

       //CALLING THE BREAKTIME SERVICE :
        if(pause != 0) {
           // int pauseMin = PausesEnum.getHours(pause);
            BreakDTO breakDTO = new BreakDTO(id_employee, pause);
            IBreakTimeService _breakTimeService = new BreakTimeService();
            try {
                _breakTimeService.insertBreakTime(breakDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}