package com.projekt.projekt.Controllers;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailController {
    @Autowired
    JavaMailSender sender;

    public void sendMail(String to, String subject, String text, String zal) throws MessagingException{
        try{
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("selenecinema@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            FileSystemResource file = new FileSystemResource(new File(zal));
            helper.addAttachment("Bilet", file);
            
            sender.send(message);
        }catch(MessagingException e){
            e.printStackTrace();
        }
        
        

    }
}
