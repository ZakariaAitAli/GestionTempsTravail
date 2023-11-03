package Servlets;

import DAO.Environment.BreakTimeService;
import DAO.Environment.WorkHoursService;
import DTO.BreakDTO;
import DTO.WorkHoursDTO;
import Interfaces.Services.IBreakTimeService;
import Interfaces.Services.IWorkHoursService;
import Shared.Enums.PausesEnum;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;


@WebServlet(name = "Servlets.workTimeServlet", value = "/Servlets.workTimeServlet")
public class workTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id_employee = 1;
        IWorkHoursService _workHoursService = new WorkHoursService();

        try {
            boolean isStart = _workHoursService.checkEndWork(id_employee);
            boolean isEnd = _workHoursService.checkStartWork(id_employee);

            JsonObject json = new JsonObject();
            json.addProperty("isStart", isStart);
            json.addProperty("isEnd", isEnd);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //SESSION :
        HttpSession session=request.getSession();
        int id_employee = 1;

        //GETTING POST REQUEST VALUES
        Time startTime = request.getParameter("start_time").isEmpty()? null: Time.valueOf(request.getParameter("start_time")) ;
        Time endTime = request.getParameter("end_time").isEmpty()? null: Time.valueOf(request.getParameter("end_time")) ;
        //int pause = parseInt(request.getParameter("pause") );

        int pause = 1;

        //CALLING WorkHours SERVICES
        WorkHoursDTO workHoursDTO = new WorkHoursDTO(id_employee,startTime,endTime) ;
        IWorkHoursService _workHoursService = new WorkHoursService();
        try {
            boolean succeded = _workHoursService.insertTime(workHoursDTO);
            if(succeded) {
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
                response.setStatus(HttpServletResponse.SC_OK);
            }else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (Exception e) {throw new RuntimeException(e);}


    }
}