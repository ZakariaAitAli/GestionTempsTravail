package Servlets;

import DAO.employeeService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", value = "/Servlets.LoginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/JSP/home.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uemail=request.getParameter("email");
        String upassword=request.getParameter("password");
        HttpSession session=request.getSession();
        try {
            employeeService emp = new employeeService();
            if(emp.login(uemail,upassword)){
                session.setAttribute("email",uemail);
                System.out.println(uemail);
               // response.sendRedirect("Servlets.HomeServlet");
                 this.getServletContext().getRequestDispatcher("/JSP/home.jsp").forward(request, response);
                System.out.println("registerok");
            }
            else{
               // request.setAttribute("status","failed");
                System.out.println("registerono");
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }catch (Exception e){


            e.printStackTrace();

        }
    }
}