package Beans;
import DAO.Environment.EmployeeService;
import DAO.Environment.ReportingService;
import DTO.EmployeeDTO;
import Interfaces.Services.IReportingService;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Integer.parseInt;

public class ReportGenerator {

    public static void main(String[] args) {
        try {
            ReportGenerator.CronJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CronJob() throws  Exception {
        IReportingService _reportingService = new ReportingService();
        HashMap<String, ArrayList<EmployeeDTO>> employeeData = fetchData();
        Set<String> keys = employeeData.keySet();
        for(String key : keys) {
            String email = key.split("/")[0];
            String fullName = key.split("/")[1];
            String idEmployee = key.split("/")[2];

            _reportingService.insertReport(parseInt(idEmployee));
            generateReport(email,fullName,idEmployee,employeeData.get(key));

        }
    }
    public static HashMap<String , ArrayList<EmployeeDTO>> fetchData() throws Exception {

        EmployeeService emp = new EmployeeService() ;
        HashMap<String, ArrayList<EmployeeDTO>> data = emp.GetAll() ;
        return data;

    }


    public static void generateReport(String email, String fullName, String idEmployee, ArrayList<EmployeeDTO> dayData) throws Exception {
        double totalWorkHours =0;
        int totalBreakTime =0;
        double suppHours =0 ;
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String path = "C:\\Users\\Simofatt\\IdeaProjects\\GestionTempsTravail\\src\\main\\java\\Shared\\Reports\\WeeklyReport" +idEmployee+formattedDate+ ".pdf";

        // Creating a path to the pdf
        String imagePath = "";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        // Creating a table that contain 2 columns
        float two_col150 = 390f;
        float columnWidth[] = { two_col150 };

        // CUSTOM BORDER :
        SolidBorder gb = new SolidBorder(Color.BLACK, 1f / 2f);

        // IMAGE LOGO :
       // ImageData imageData = ImageDataFactory.create(imagePath);
        //Image image = new Image(imageData);

        float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float y = pdfDocument.getDefaultPageSize().getHeight() / 2;

        //image.setFixedPosition(x - 70, y + 280);
        ///image.setHeight(112);
        //document.add(image);
        // HEADER OF THE DOC :
        //document.add(new Paragraph("Report work hours").setFontSize(10F));

        // Creating a table object that have an array as a parameter
        // And adding a new cell to the array so i can write on it

       Table table = new Table(columnWidth);
        table.setMarginLeft(90f);
        table.addCell(new Cell().add("Report work hours").setBold().setBorder(gb).setFontSize(20F)
                .setPaddingLeft(60));
        document.add(table);

        // BODY OF THE DOC :
        document.add(new Paragraph("\r\n Nom Complet : " + fullName + "\n"));
        document.add(new Paragraph("CID : " + idEmployee + "\n"

                +  "Email :   " + email + " \r\n" + "\n").setFontSize(10F));

        Table table2 = new Table(4);
        table2.addCell(new Cell().add(" " + "Date ").setFontSize(10F).setBold());
        table2.addCell(new Cell().add(" " + "Work hours").setFontSize(10F).setBold());
        table2.addCell(new Cell().add(" " + "Pauses").setFontSize(10F).setBold());
        table2.addCell(new Cell().add(" " + "Supplementary hours").setFontSize(10F).setBold());

        for (EmployeeDTO data : dayData) {

            table2.addCell(new Cell().add(String.valueOf(data.date)).setFontSize(10F));
            table2.addCell(new Cell().add(String.valueOf(data.hoursWorkedAfterPause).contains(".0")  ? String.valueOf((int) data.hoursWorkedAfterPause) : String.valueOf(data.hoursWorkedAfterPause)).setFontSize(10F));
            table2.addCell(new Cell().add(String.valueOf((data.pause))).setFontSize(10F));
            table2.addCell(new Cell().add(String.valueOf(data.hoursSupp).contains(".0") ? String.valueOf((int) data.hoursSupp): String.valueOf(data.hoursSupp)).setFontSize(10F));

            totalWorkHours += data.hoursWorkedAfterPause;
            totalBreakTime += data.pause;
            suppHours += data.hoursSupp ;
        }

        document.add(table2);




        document.add(new Paragraph("\n \n \n \n Weekly Summary for Week Ending  :        " + formattedDate +"\n \n \n \n").setBold())
                .setFontSize(10F);

        Table summaryTable = new Table(3);
        summaryTable.addCell("Total Work Hours:  " +totalWorkHours );
        summaryTable.addCell("Total Pauses:   "+totalBreakTime  );
        summaryTable.addCell("Total Supplementary Hours:   " +suppHours);
        document.add(summaryTable);





        document.close();


    }
}




