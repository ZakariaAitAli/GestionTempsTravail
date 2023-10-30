package Servlets;

import DAO.employeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;

@WebServlet(name = "workTimeServlet", value = "/workTimeServlet")
public class workTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Time start_time = Time.valueOf(request.getParameter("start_time"));
        Time end_time = Time.valueOf(request.getParameter("end_time"));
        String[] pauses = request.getParameterValues("pause");
        HttpSession session=request.getSession();

        //int id_employee = session.getAttribute();
        //employeeService emp = new employeeService();
        //emp.insertTime(id_employee, start_time, end_time, pauses);

    }
}