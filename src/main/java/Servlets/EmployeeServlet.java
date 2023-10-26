package Servlets;


import DAO.Employee;
import DAO.employeeList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/Servlets.EmployeeServlet")
//@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        employeeList employeeList = new employeeList();
        List<Employee> employees = employeeList.getAllEmployees();

        // Transmettez la liste des employés à la JSP
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/JSP/dashboard1.jsp").forward(request, response);
    }
}
