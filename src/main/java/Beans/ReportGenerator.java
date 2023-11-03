package Beans;
import DAO.Environment.EmployeeService;
import DAO.Environment.ReportingService;
import DTO.EmployeeDTO;
import Interfaces.Services.IReportingService;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.Document;
import java.text.DecimalFormat;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.itextpdf.layout.property.TextAlignment;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    static DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format pour arrondir à deux chiffres après la virgule

    public static void generateReport(String email, String fullName, String idEmployee, ArrayList<EmployeeDTO> dayData) throws Exception {
        double totalWorkHours = 0;
        int totalBreakTime = 0;
        double suppHours = 0;
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String path = "/Users/mac/Desktop/gestion_test_/GestionTempsTravail/src/main/java/Shared/Reports/WeeklyReport" + idEmployee + formattedDate + ".pdf";

        // Creating a path to the pdf
        String imagePath = "";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        // Adding a professional header
        Paragraph header = new Paragraph("Weekly Work Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(24f)
                .setBold();

        document.add(header);

        // BODY OF THE DOC :
        document.add(new Paragraph("\n\nFull Name: " + fullName)
                .setFontSize(12F));
        document.add(new Paragraph("CID: " + idEmployee + "\nEmail: " + email)
                .setFontSize(12F));

        Table table = new Table(new float[]{2, 2, 2, 2});
        table.setWidthPercent(100);

        // Adding table headers with background color
        Cell dateHeader = new Cell().add("Date").setBold().setFontSize(12F);
        Cell workHoursHeader = new Cell().add("Work Hours").setBold().setFontSize(12F);
        Cell pausesHeader = new Cell().add("Pauses").setBold().setFontSize(12F);
        Cell suppHoursHeader = new Cell().add("Supplementary Hours").setBold().setFontSize(12F);

        Color headerColor = new DeviceRgb(0, 123, 255);
        dateHeader.setBackgroundColor(headerColor);
        workHoursHeader.setBackgroundColor(headerColor);
        pausesHeader.setBackgroundColor(headerColor);
        suppHoursHeader.setBackgroundColor(headerColor);

        table.addHeaderCell(dateHeader);
        table.addHeaderCell(workHoursHeader);
        table.addHeaderCell(pausesHeader);
        table.addHeaderCell(suppHoursHeader);

        // Adding data rows to the table
        for (EmployeeDTO data : dayData) {
            table.addCell(new Cell().add(String.valueOf(data.date)).setFontSize(10F));
            double roundedHoursWorkedAfterPause = Math.round(data.hoursWorkedAfterPause * 100.0) / 100.0;
            table.addCell(new Cell().add(decimalFormat.format(roundedHoursWorkedAfterPause)).setFontSize(10F));
            double roundedPause = Math.round(data.pause * 100.0) / 100.0;
            table.addCell(new Cell().add(decimalFormat.format(roundedPause)).setFontSize(10F));
            double roundedHoursSupp = Math.round(data.hoursSupp * 100.0) / 100.0;
            table.addCell(new Cell().add(decimalFormat.format(roundedHoursSupp)).setFontSize(10F));

            totalWorkHours += roundedHoursWorkedAfterPause;
            totalBreakTime += roundedPause;
            suppHours += roundedHoursSupp;
        }

        document.add(table);

        // Adding a summary section
        Paragraph summary = new Paragraph()
                .setFontSize(12F)
                .add("\n\nWeekly Summary for Week Ending: " + formattedDate + "\n");


        document.add(summary);

        // Adding a table for the totals
        Table totalsTable = new Table(new float[]{2, 2});
        totalsTable.setWidthPercent(50);
        totalsTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

        totalsTable.addCell(new Cell().add("Total Work Hours:").setBold().setFontSize(12F));
        totalsTable.addCell(new Cell().add(String.valueOf(totalWorkHours)).setFontSize(12F));
        totalsTable.addCell(new Cell().add("Total Pauses:").setBold().setFontSize(12F));
        totalsTable.addCell(new Cell().add(String.valueOf(totalBreakTime)).setFontSize(12F));
        totalsTable.addCell(new Cell().add("Total Supplementary Hours:").setBold().setFontSize(12F));
        totalsTable.addCell(new Cell().add(String.valueOf(suppHours)).setFontSize(12F));

        document.add(totalsTable);

        document.close();
    }

}