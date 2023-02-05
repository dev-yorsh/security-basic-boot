package com.application.app.service.notification.impl;

import com.application.app.service.notification.email.EmailService;
import com.application.app.service.notification.email.model.EmailDetail;
import com.application.core.exception.http.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendSimpleEmail(EmailDetail emailDetail) {
        log.info("[EmailServiceImpl | sendSimpleEmail]: start...");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(emailDetail.getSubject());
            mailMessage.setFrom(emailDetail.getSender());
            mailMessage.setTo(emailDetail.getTo());
            mailMessage.setText(emailDetail.getBody());
            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            throw new InternalException(exception.getMessage());
        }
    }

    @Override
    public void sendHtmlEmail(EmailDetail emailDetail) {
        log.info("[EmailServiceImpl | sendHtmlEmail]: start...");
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject(emailDetail.getSubject());
            message.setFrom(emailDetail.getSender());
            message.setRecipients(MimeMessage.RecipientType.TO, emailDetail.getTo());
            message.setContent(emailDetail.getBody(), "text/html; charset=utf-8");
            javaMailSender.send(message);
        } catch (Exception exception) {
            throw new InternalException(exception.getMessage());
        }
    }
}
