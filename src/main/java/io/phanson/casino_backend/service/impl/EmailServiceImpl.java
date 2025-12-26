package io.phanson.casino_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import io.phanson.casino_backend.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error while sending email: " + e.getMessage());
        }
    }

    public void sendHtmlEmail(@NonNull String to, @NonNull String subject, @NonNull String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Error while sending HTML email: " + e.getMessage());
        }
    }

    public void sendVerificationToken(@NonNull String userEmail, @NonNull String token) {
        String subject = "Verification Code";
        String htmlForm = """
                                <!DOCTYPE html>
                <html lang="en">
                  <head>
                    <meta charset="UTF-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <title>Verification Code</title>
                    <style>
                        #verification-section{
                            padding: 10px 30px;
                            background-color: rgba(190, 190, 190, 0.484);
                            width: 50%;
                        }
                    </style>
                  </head>
                  <body>
                    <h1>Verification Code</h1>
                    <p>Here is your token for Verification:</p>
                    <div id="verification-section">
                      <h3>{{TOKEN}}</h3>
                    </div>
                    <p>Thanks for using my service.</p>
                  </body>
                </html>

                                """.replace("{{TOKEN}}", token);
        sendHtmlEmail(userEmail, subject, htmlForm);
    }
}
