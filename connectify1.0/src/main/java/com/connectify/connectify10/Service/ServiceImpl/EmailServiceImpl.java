package com.connectify.connectify10.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.connectify.connectify10.Service.EmailService;

import lombok.Value;
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender eMailSender;

   

    @Override
    public void sendEmail(String to, String subject, String body) {

       SimpleMailMessage message = new SimpleMailMessage();
       message.setTo(to);
       message.setSubject(subject);
       message.setText(body);
       message.setFrom("verify@connectify.com");

       eMailSender.send(message);


      
    }

    @Override
    public void sendEmilWithHtml() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmilWithHtml'");
    }

    @Override
    public void sendEmilWithAttachment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmilWithAttachment'");
    }

}
