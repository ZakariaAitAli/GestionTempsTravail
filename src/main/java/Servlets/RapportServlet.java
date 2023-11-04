package Servlets;

import DAO.Environment.ReportingService;
import DTO.ReportingDTO;
import Interfaces.Services.IReportingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(name = "Servlets.RapportServlet", value = "/Servlets.RapportServlet")

public class RapportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IReportingService _reportingService = new ReportingService();
        String id = request.getParameter("id");
        try {
            ArrayList<ReportingDTO> reports  = _reportingService.getReport(parseInt(id));
           // request.setAttribute("reports", reports);
           // request.setAttribute("reports", reports);


          JsonObject json = new JsonObject();

            Gson gson = new Gson();


            String reportsJson = gson.toJson(reports);

            json.addProperty("reports", reportsJson);


            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json.toString());
          // request.getRequestDispatcher("/JSP/rapportList.jsp").forward(request, response);
        } catch (Exception ignored) {

        }

    }
}


