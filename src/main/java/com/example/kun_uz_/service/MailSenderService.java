/* package com.example.kun_uz_.service;

import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String fromAccount;
 @Value("${server.host}")
 private String serverHost;
    public  void sendRegistrationEmail(String toAccount){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Registration verification\n");
        stringBuilder.append("Clic to below to complete registration\n");
        stringBuilder.append("Link:");
        stringBuilder.append("http//localhost:8080/api/v1/auth/email/verification/");
        stringBuilder.append(JwtUtil.encode(toAccount));
        sendEmail(toAccount, "Registration",stringBuilder.toString());


    }
    public void sendRegistrationEmailMime(String toAccount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h1 style=\"text-align: center\">Registration verification</h1>");
        stringBuilder.append("<br><br>");
        // <p><a href="asd.dasdad.asdaasda">Click to the link to complete registration</a></p>
        stringBuilder.append("<p><a href=\"");
        stringBuilder.append(serverHost).append("/api/v1/auth/email/verification/");
        stringBuilder.append(JwtUtil.encode(toAccount)).append("\">");
        stringBuilder.append("Click to the link to complete registration</a></p>");
        sendEmailMime(toAccount, "Registration", stringBuilder.toString());
    }

   private void sendEmail(String toAccount, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);

    }
    private void sendEmailMime(String toAccount, String subject, String text) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            msg.setFrom(fromAccount);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}*/
