package Servlets;


import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet(name = "Servlets.PdfDownloadServlet", value = "/PdfDownloadServlet")
public class PdfDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =parseInt(request.getParameter("id"));
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "C:\\Users\\Simofatt\\IdeaProjects\\GestionTempsTravail\\src\\main\\java\\Shared\\Reports\\WeeklyReport"+id+formattedDate+".pdf";

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        fis.close();
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}