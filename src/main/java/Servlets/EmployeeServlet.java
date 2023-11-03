package Servlets;


import DAO.Environment.BreakTimeService;
import DAO.Environment.MoodService;
import DAO.Environment.EmployeeService;
import Interfaces.Services.IBreakTimeService;
import Interfaces.Services.IEmployeeService;
import Interfaces.Services.IMoodService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlets.EmployeeServlet", value = "/Servlets.EmployeeServlet")

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IEmployeeService _employeeService = new EmployeeService();
        IBreakTimeService _breakService = new BreakTimeService();
        IMoodService _moodService = new MoodService();

        try {
            ArrayList<String> employees = _employeeService.GetAllEmails();
            List<Integer> pauses =_breakService.getAllPauses();
            List<Integer> humeurs = _moodService.getAllHumeurs();
            int[] humeurCounts = new int[3];
            int[] pauseCounts = new int[3];

            for (Integer pause : pauses) {
                if (pause == 1) {
                    pauseCounts[0]++;
                } else if (pause == 2) {
                    pauseCounts[1]++;
                } else if (pause == 3) {
                    pauseCounts[2]++;
                }
            }

            for (Integer humeur : humeurs) {
                if (humeur == 1) {
                    humeurCounts[0]++;
                } else if (humeur == 2) {
                    humeurCounts[1]++;
                } else if (humeur == 3) {
                    humeurCounts[2]++;
                }
            }

            request.setAttribute("bonneCount", humeurCounts[0]);
            request.setAttribute("mauvaiseCount", humeurCounts[1]);
            request.setAttribute("stableCount", humeurCounts[2]);

            request.setAttribute("matinaleCount", pauseCounts[0]);
            request.setAttribute("apresMidiCount", pauseCounts[1]);
            request.setAttribute("dejeunerCount", pauseCounts[2]);

            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/JSP/dashboard1.jsp").forward(request, response);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
