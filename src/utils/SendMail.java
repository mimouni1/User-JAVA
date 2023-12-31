package utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class SendMail {

    public static void send(User user, Map<String, String> data) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mait.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ikbel.mimouni@esprit.tn", "svswwqqezvkjnclh");
            }
        });

        String htmlFilePath = "./src/gui/emailTemplate.html";
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

            htmlContent = htmlContent.replace("[titlePlaceholder]", data.get("titlePlaceholder"));
            htmlContent = htmlContent.replace("[msgPlaceholder]", data.get("msgPlaceholder"));
            htmlContent = htmlContent.replace("[codePlaceholder]", Integer.toString(user.getVerificationCode()));

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ikbel.mimouni@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(data.get("emailSubject"));
            // message.setText("Hello, this is a test email from JavaFX.");
            message.setContent(htmlContent, "text/html");

            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
