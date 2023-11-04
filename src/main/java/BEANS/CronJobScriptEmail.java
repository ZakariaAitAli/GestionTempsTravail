package Beans;
import DAO.Environment.EmployeeService;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import static java.lang.Integer.parseInt;

public class CronJobScriptEmail {
    static final String SENDER_EMAIL = "nada.samadi@etu.uae.ac.ma";
    static final String SENDER_PASSWORD = "ruos skjk fape leaf";


    static final String SUBJECT = "Sujet : Votre Humeur est Important pour Nous !";

    public static void main(String[] args) throws Exception {
             EmployeeService emp = new EmployeeService();
              int idEmployee = 0 ;
        ArrayList<String> emails = emp.GetAllEmails() ;
             for(String email :emails) {;
                 sendEmail(email.split("/")[0],parseInt(email.split("/")[2]));

             }
    }

    public static void sendEmail(String toEmail,int IdEmployee) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(SUBJECT);
            message.setText("Cher(e) Employé(e),\n" +
                    "\n" +
                    "Nous espérons que vous passez une excellente journée !\n" +
                    "\n" +
                    "Dans le cadre de notre engagement à améliorer continuellement notre environnement de travail, nous aimerions recueillir vos retours sur votre expérience au sein de notre équipe.\n" +
                    "\n" +
                    "Veuillez prendre un moment pour partager votre humeur actuelle en cliquant sur le lien ci-dessous : :\n" +
                    "\n" +
                    " http://localhost:8080/GestionTempsTravail/Servlets.Mood?id="+IdEmployee+" \n" +
                    "\n" +
                    "Nous vous remercions par avance pour votre contribution. Votre contrubution est essentielle pour nous aider à faire de notre entreprise un endroit où chacun se sent valorisé et épanoui.\n" +
                    "\n" +
                    "Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.\n" +
                    "\n" +
                    "Merci encore pour votre participation !\n" +
                    "\n" +
                    "Cordialement,\n" +
                    "GTT COMPANY\n" +
                    "WorkTime Manager\n");

            Transport.send(message);

            System.out.println("Email envoyé à : " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
