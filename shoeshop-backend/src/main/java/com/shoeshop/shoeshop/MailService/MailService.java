package com.shoeshop.shoeshop.MailService;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
@RequestMapping("/E-mail")
@CrossOrigin(origins = "*", maxAge = 3600)
@Service
public class MailService {

    private JavaMailSender emailSender;

    public MailService(@Lazy JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("shoeshopssbg@gmail.com");
        mailSender.setPassword("fzrppvxcahuocwou");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    @PostMapping("/send")
    public void SendSimpleMessage(@RequestBody MailRequest mailRequest)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailRequest.getFrom());
        message.setTo(mailRequest.getTo());
        message.setSubject(mailRequest.getTopic());
        message.setText(mailRequest.getText());
        emailSender.send(message);
}

}
