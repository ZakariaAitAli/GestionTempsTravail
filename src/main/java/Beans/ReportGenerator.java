package Beans;
import DAO.employeeService;
import DTO.EmployeeDTO;
import Models.Employee;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.concurrent.TimeUnit;

public class ReportGenerator {

    public static void main(String[] args) {
        try {
            ReportGenerator.CronJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CronJob() throws  Exception {
        HashMap<String, ArrayList<EmployeeDTO>> employeeData = fetchData();
        Set<String> keys = employeeData.keySet();
        for(String key : keys) {
            String email = key.split("/")[0];
            String fullName = key.split("/")[1];
            String idEmployee = key.split("/")[2];

            generateReport(email,fullName,idEmployee,employeeData.get(key));
        }

        }
    public static HashMap<String , ArrayList<EmployeeDTO>> fetchData() throws Exception {

        employeeService emp = new employeeService() ;
        HashMap<String, ArrayList<EmployeeDTO>> data = emp.GetAll() ;
        return data;

    }


    public static void generateReport(String email, String fullName, String idEmployee, ArrayList<EmployeeDTO> dayData) throws Exception {

        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String filePath = "C:\\Users\\Simofatt\\IdeaProjects\\GestionTempsTravail\\src\\main\\java\\Shared\\Reports\\WeeklyReport" +idEmployee+  ".pdf";
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(filePath));
        Document document = new Document(pdfDocument);

        // Add title
        document.add(new Paragraph("Weekly Work Report").setTextAlignment(TextAlignment.CENTER));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        document.add(new Paragraph("Full Name: " + fullName));
        document.add(new Paragraph("Email: " + email));
        document.add(new Paragraph("ID: " + idEmployee));

        // Add daily work hours
        Table workTable = new Table(4);
        workTable.addCell("Date");
        workTable.addCell("Work Hours");
      //  workTable.addCell("Pauses");
        workTable.addCell("Supplementary Hours");

        for(EmployeeDTO data :  dayData  ) {
            workTable.addCell(dateFormat.format(data.date));
            workTable.addCell(String.valueOf(data.hoursWorkedAfterPause));
            // workTable.addCell(String.valueOf(dayData.getPauses()));
            workTable.addCell(String.valueOf(data.hoursSupp));

        }
        document.add(workTable);

        // Add weekly summary
        document.add(new Paragraph("Weekly Summary for Week Ending " + dateFormat.format(new Date())));
        Table summaryTable = new Table(3);
        summaryTable.addCell("Total Work Hours: "  );
        summaryTable.addCell("Total Pauses: "  );
        summaryTable.addCell("Total Supplementary Hours: " );

        document.add(summaryTable);
        document.close();
        }



}


