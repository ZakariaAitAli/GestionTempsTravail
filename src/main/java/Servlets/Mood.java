package Servlets;

import DAO.Environment.MoodService;
import Interfaces.Services.IMoodService;
import com.google.gson.JsonObject;

import javax.persistence.Id;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;
import java.time.LocalTime;

@WebServlet(name = "Servlets.Mood", value = "/Servlets.Mood")
public class Mood extends HttpServlet {
    LocalTime heureActuelle = LocalTime.now();
    LocalTime heureReference = LocalTime.of(18, 0);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IMoodService _moodService = new MoodService();


        try {
            int IdEmployee = parseInt(request.getParameter("id"));


                boolean IsSubmit = _moodService.CheckMood(IdEmployee) ;

                JsonObject json = new JsonObject();
                json.addProperty("isSubmit", IsSubmit);
                json.addProperty("isHours", heureActuelle.isAfter(heureReference));

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json.toString());



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IMoodService _moodService = new MoodService();

        int mood =parseInt(request.getParameter("humeur"));
        int IdEmployee = parseInt(request.getParameter("id"));

        try {
            _moodService.insertMood(mood);
            _moodService.MoodSubmited(IdEmployee);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}