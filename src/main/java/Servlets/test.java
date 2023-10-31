package Servlets;

import DAO.employeeService;
import DTO.EmployeeDTO;
import org.json.JSONObject;

import org.json.JSONObject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

@WebServlet(name = "Servlets.test", value = "/Servlets.test")
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // this.getServletContext().getRequestDispatcher("/JSP/test.jsp").forward(request, response);
       employeeService emp = new employeeService() ;
        try {


            HashMap<String, ArrayList<EmployeeDTO>> data = emp.GetAll() ;
            Gson gson = new Gson();
            String json = gson.toJson(data);


            response.setContentType("application/json");

            // Write JSON data to the response
            response.getWriter().write(json);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}