package Servlets;

import DAO.Environment.BreakTimeService;
import DAO.Environment.MoodService;
import DAO.Environment.EmployeeService;
import Interfaces.Services.IBreakTimeService;
import Interfaces.Services.IEmployeeService;
import Interfaces.Services.IMoodService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlets.RapportServlet", value = "/Servlets.RapportServlet")

public class RapportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("idEmployee");
        IEmployeeService _employeeService = new EmployeeService();
        IBreakTimeService _breakService = new BreakTimeService();
        IMoodService _moodService = new MoodService();


        List<String> rapports = null;


        request.setAttribute("rapports", rapports);
        request.getRequestDispatcher("/JSP/rapportList.jsp").forward(request, response);

    }
}


