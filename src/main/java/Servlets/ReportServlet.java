package Servlets;


import Beans.ReportGenerator;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlets.ReportServlet", value = "/Servlets.ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportGenerator.generateReport();
        this.getServletContext().getRequestDispatcher("/JSP/test.jsp?success=1").forward(request, response);

    }
}