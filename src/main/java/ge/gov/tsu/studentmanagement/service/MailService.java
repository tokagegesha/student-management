package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.exception.TsuRuntimeException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailService {


    public static void sendMail(String username, String password, String from, String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            //message.setText(text);
            message.setContent(text, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("sent");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new TsuRuntimeException(e.getMessage());
        }
    }


}
