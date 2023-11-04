package Servlets;

import DAO.Identity.AuthentificationService;
import DAO.Environment.EmployeeService;
import Interfaces.Services.IAuthentificationService;

import java.io.IOException;

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
            EmployeeService emp = new EmployeeService();
            IAuthentificationService auth = new AuthentificationService();
            int idEmployee = emp.getId(uemail) ;
            if(auth.login(uemail,upassword)){

                session.setAttribute("email",uemail);
                session.setAttribute("idEmployee", idEmployee);
                if(auth.isAdmin(uemail,upassword)){
                    this.getServletContext().getRequestDispatcher("/JSP/dashboard1.jsp").forward(request, response);

                }
                else {
                    this.getServletContext().getRequestDispatcher("/JSP/home.jsp").forward(request, response);

                }

                // response.setStatus(HttpServletResponse.SC_OK);
                // Create a JSON object to include the idEmployee
                // JSONObject jsonResponse = new JSONObject();
                // jsonResponse.put("idEmployee", idEmployee);

                // Write the JSON response to the output stream
                // PrintWriter out = response.getWriter();
                // out.print(jsonResponse.toString());
                // out.flush();

            }
            else{
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}