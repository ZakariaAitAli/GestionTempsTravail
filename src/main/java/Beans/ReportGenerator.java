package Beans;
import DAO.employeeService;
import DTO.EmployeeDTO;
import Models.Employee;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timer;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class ReportGenerator {

    public static void main(String[] args) {
        try {
            ReportGenerator.generateReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String , ArrayList<EmployeeDTO>> fetchData() throws Exception {

        employeeService emp = new employeeService() ;
        HashMap<String, ArrayList<EmployeeDTO>> data = emp.GetAll() ;
        return data;

    }

    // @Schedule(dayOfWeek = "3", hour = "19", minute = "55", second = "0", persistent = false)
    public static void generateReport() throws Exception {

        HashMap<String , ArrayList<EmployeeDTO>> data = fetchData();

        String path = "\\mnt\\c\\Users\\Simofatt\\IdeaProjects\\GestionTempsTravail\\src\\main\\java\\Shared\\Reports\\D" + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        SolidBorder gb = new SolidBorder(Color.BLACK, 1f / 2f);

        // Creating a table that contain 2 columns
        float two_col150 = 300f;
        float columnWidth[] = {two_col150};


        float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float y = pdfDocument.getDefaultPageSize().getHeight() / 2;


        float a = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float b = pdfDocument.getDefaultPageSize().getHeight() / 2;


        float z = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float t = pdfDocument.getDefaultPageSize().getHeight() / 2;


        // HEADER OF THE DOC :
        document.add(new Paragraph("ROYAUME DU MAROC \r\n" + "Université Abdelmalek Essaadi \r\n"
                + "Ecole Nationale des Sciences \r\n" + "Appliquées \r\n" + "Tetouan \r\n"
                + "Service des Affaires Estudiantines                \r\n \r\n").setFontSize(10F));


        Table table = new Table(columnWidth);
        table.setMarginLeft(100f);

        table.addCell(new Cell().add(" " + "ATTESTATION DE REUSSITE ").setBold().setBorder(gb).setFontSize(20F)
                .setPaddingLeft(10));
        document.add(table);

        // Writing the body on the document
        document.add(new Paragraph(
                "\r\n" + "Le Directeur de l'Ecole Nationale des Sciences Appliquées atteste que :  " + "\r\n")
                .setFontSize(10F));
        document.add(new Paragraph( "\r\n").setBold().setFontSize(10F));

        document.add(new Paragraph("Numéro de la carte d’identité nationale :   " + "\r\n" + "\n"
                + "Code national de l’étudiant :   " + " \r\n" + "\n" + "N°etudiant :   " +
                "\r\n" + "\n" + "Né le :   " + "\r\n" + "\n"
                + "A été déclare admis au : "
                + "au titre de l'année universitaire 2020/2021 \r\n \n \n\n\n").setFontSize(10F));
        document.add(new Paragraph("Fait à TETOUAN, le ").setPaddingLeft(300).setFontSize(10F));


        Table table5 = new Table(1);
        table5.addCell(
                new Cell()
                        .add(" \n \n \n \n \n \n \n \n \n Adresse : M'Hannech II \n "
                                + "          B.P . 2222 Tétouan \n" + "      Tél: 0539968802 FAX : 05399994624\n")
                        .setBorder(Border.NO_BORDER).setFontSize(10F));
        document.add(table5);

        // Closing the document
        document.close();


    }
}
