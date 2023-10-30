package Servlets;


import Beans.HumeurCalcul;
import Beans.PauseCalcul;
import DAO.Employee;
import DAO.Humeur;
import DAO.Pause;
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
        System.out.println("Number of employees: " + employees.size());

        PauseCalcul pauseCalcul = new PauseCalcul();

        List<Pause> pauses = pauseCalcul.getAllPauses();
        int[] pauseCounts = new int[3];

        for (Pause pause : pauses) {
            if (pause.getPause() == 1) {
                pauseCounts[0]++;
            } else if (pause.getPause() == 2) {
                pauseCounts[1]++;
            } else if (pause.getPause() == 3) {
                pauseCounts[2]++;
            }
        }

        HumeurCalcul humeurCalcul = new HumeurCalcul();

        List<Humeur> humeurs = humeurCalcul.getAllHumeurs();
        int[] humeurCounts = new int[3];

        for (Humeur humeur : humeurs) {
            if (humeur.getHumeur() == 1) {
                humeurCounts[0]++;
            } else if (humeur.getHumeur() == 2) {
                humeurCounts[1]++;
            } else if (humeur.getHumeur() == 3) {
                humeurCounts[2]++;
            }
        }

        request.setAttribute("bonneCount", humeurCounts[0]);
        request.setAttribute("mauvaiseCount", humeurCounts[1]);
        request.setAttribute("stableCount", humeurCounts[2]);



        request.setAttribute("matinaleCount", pauseCounts[0]);
        request.setAttribute("apresMidiCount", pauseCounts[1]);
        request.setAttribute("dejeunerCount", pauseCounts[2]);





        // Transmettez la liste des employés à la JSP
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/JSP/dashboard1.jsp").forward(request, response);
    }
}
