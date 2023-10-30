package Servlets;

import Beans.HumeurCalcul;
import Beans.PauseCalcul;
import DAO.Humeur;
import DAO.Pause;
import DAO.Rapport;
import Beans.RapportList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RapportServlet", value = {"/Servlets.RapportServlet"})

public class RapportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeId = request.getParameter("id");
        System.out.println("Employee ID: " + employeeId); // Ajoutez cette ligne
        // Utilisez cet ID pour extraire les rapports de l'employé depuis la base de données
        List<Rapport> rapports = RapportList.getRapportsByEmployeeId(Integer.parseInt(employeeId));
        System.out.println("Number of reports: " + rapports.size());
        // Transmettez la liste des rapports à une JSP pour l'affichage

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



        request.setAttribute("rapports", rapports);
        request.getRequestDispatcher("/JSP/rapportList.jsp").forward(request, response);
    }
}


