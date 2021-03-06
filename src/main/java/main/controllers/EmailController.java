package main.controllers;

import main.service.AuthService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
// искусственный коммит 9.5.22
@RestController
public class EmailController {
    private final JavaMailSender mailSender;
    private final AuthService authService;

    public EmailController(JavaMailSender mailSender, AuthService authService) {
        this.mailSender = mailSender;
        this.authService = authService;
    }

    @RequestMapping("/send")
    public String send() {
        String from = "remenyuk.valery@yandex.ru";
        String to = "remenyuk79@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Это тема письма");
        message.setText("Воу! А это текст письма! Это просто текст, без оформления и HTML!");
        mailSender.send(message);

        return "Письмо отправлено в " + LocalDateTime.now();    }
}