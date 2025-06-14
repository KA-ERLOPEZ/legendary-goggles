package com.sistema.pizzeria.elpirata.api.core.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sistema.pizzeria.elpirata.api.core.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;
    
    @Value("${sendgrid.from.email}")
    private String fromEmail;

    // Método para enviar correos electrónicos con SendGrid API v3 
    private void sendEmail(Mail mail) throws IOException {
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Código de respuesta: " + response.getStatusCode());
        } catch (IOException ex) {
            throw ex;
        }
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            Email from = new Email(fromEmail);
            Email recipient = new Email(to);
            Content content = new Content("text/plain", text);
            Mail mail = new Mail(from, subject, recipient, content);
            sendEmail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String text) {
        try {
            Email from = new Email(fromEmail);
            Email recipient = new Email(to);
            Content content = new Content("text/html", text);
            Mail mail = new Mail(from, subject, recipient, content);
            sendEmail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            Email from = new Email(fromEmail);
            Email recipient = new Email(to);
            Content content = new Content("text/plain", text);
            Mail mail = new Mail(from, subject, recipient, content);

            // Leer archivo y convertirlo en Base64
            Path path = Path.of(pathToAttachment);
            byte[] fileBytes = Files.readAllBytes(path);
            String encodedFile = Base64.getEncoder().encodeToString(fileBytes);

            // Crear y adjuntar el archivo
            Attachments attachment = new Attachments();
            attachment.setFilename(path.getFileName().toString());
            attachment.setType(Files.probeContentType(path));
            attachment.setDisposition("attachment");
            attachment.setContent(encodedFile);

            mail.addAttachments(attachment);
            sendEmail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageWithInlineResource(String to, String subject, String text, String pathToInlineResource, String cid) {
        try {
            Email from = new Email("tu-email@elpirata.com");
            Email recipient = new Email(to);
            Content content = new Content("text/html", text);
            Mail mail = new Mail(from, subject, recipient, content);

            // Leer archivo y convertirlo en Base64
            Path path = Path.of(pathToInlineResource);
            byte[] fileBytes = Files.readAllBytes(path);
            String encodedFile = Base64.getEncoder().encodeToString(fileBytes);

            // Crear y adjuntar el archivo en línea
            Attachments attachment = new Attachments();
            attachment.setFilename(path.getFileName().toString());
            attachment.setType(Files.probeContentType(path));
            attachment.setDisposition("inline");
            attachment.setContent(encodedFile);
            attachment.setContentId(cid);

            mail.addAttachments(attachment);
            sendEmail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

