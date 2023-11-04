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

import static java.lang.Integer.parseInt;


@WebServlet(name = "Servlets.workTimeServlet", value = "/Servlets.workTimeServlet")
public class workTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
   String idEmployee = request.getParameter("idEmployee");
    if(idEmployee == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else {
            int id_employee = parseInt(idEmployee);
            IWorkHoursService _workHoursService = new WorkHoursService();

            try {
                boolean isEnd = _workHoursService.checkEndWork(id_employee);
                boolean isStart = _workHoursService.checkStartWork(id_employee);

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
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //SESSION :
        HttpSession session=request.getSession();
        if(request.getParameter("idEmployee")==null) {
            response.setStatus(499);
        }else {
            int id_employee = parseInt(request.getParameter("idEmployee"));

            //GETTING POST REQUEST VALUES
            Time startTime = request.getParameter("start_time").isEmpty() ? null : Time.valueOf(request.getParameter("start_time"));
            Time endTime = request.getParameter("end_time").isEmpty() ? null : Time.valueOf(request.getParameter("end_time"));
            Time TimeOfBreak = request.getParameter("pause").isEmpty() ? null : Time.valueOf(request.getParameter("pause"));

            //CALLING WorkHours SERVICES
            WorkHoursDTO workHoursDTO = new WorkHoursDTO(id_employee, startTime, endTime);
            IWorkHoursService _workHoursService = new WorkHoursService();
            try {
                boolean succeded = _workHoursService.insertTime(workHoursDTO);
                if (succeded) {
                    //CALLING THE BREAKTIME SERVICE :
                    if (TimeOfBreak != null) {
                        int pause = PausesEnum.calculateValue(TimeOfBreak);
                       // int pauseEnum = PausesEnum.getHours(pause) ;
                        BreakDTO breakDTO = new BreakDTO(id_employee, pause);
                        IBreakTimeService _breakTimeService = new BreakTimeService();
                        try {

                            _breakTimeService.insertBreakTime(breakDTO);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}