package com.application.app.service.notification.impl;

import com.application.app.domain.security.User;
import com.application.app.service.notification.NotificationService;
import com.application.app.service.notification.email.EmailService;
import com.application.app.service.notification.email.model.EmailDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendEmailAccountConfirmation(User userData, String urlConfirmation) {
        log.info("[NotificationServiceImpl | sendEmailConfirmationAccount]: start...");
        String htmlContent = createBodyAccountConfirmation(userData, urlConfirmation);
        EmailDetail emailModel = createEmailModel("SECURITY SYSTEM | ACTIVACIÓN DE LA CUENTA",
                userData.getEmail(), username, htmlContent);
        emailService.sendHtmlEmail(emailModel);
    }

    @Override
    public void sendEmailResetPassword(User userData, String urlResetPassword) {
        log.info("[NotificationServiceImpl | sendEmailResetPassword]: start...");
        String htmlContent = createBodyResetPassword(userData, urlResetPassword);
        EmailDetail emailModel = createEmailModel("SECURITY SYSTEM | REINICIO DE CONTRASEÑA",
                userData.getEmail(), username, htmlContent);
        emailService.sendHtmlEmail(emailModel);
    }

    @Override
    public void sendAlertEmailChangePassword(User userData) {
        log.info("[NotificationServiceImpl | sendAlertEmailChangePassword]: start...");
        String htmlContent = createBodyAlertChangePassword(userData);
        EmailDetail emailModel = createEmailModel("SECURITY SYSTEM | ALERTA - CAMBIO DE CONTRASEÑA",
                userData.getEmail(), username, htmlContent);
        emailService.sendHtmlEmail(emailModel);
    }

    private EmailDetail createEmailModel(String subject, String to, String sender, String body) {
        EmailDetail emailModel = new EmailDetail();
        emailModel.setSubject(subject);
        emailModel.setTo(to);
        emailModel.setSender(sender);
        emailModel.setBody(body);
        return emailModel;
    }

    private String createBodyAccountConfirmation(User userData, String urlConfirmation) {
        return "<p>Estimado(a) <strong>" + userData.getName() + "</strong>, </p>" +
                "Se ha creado una cuenta con tu dirección de correo, por favor haga click en el siguiente enlace </p>" +
                "para <strong>verificar su cuenta</strong>: " +
                "<p>" + urlConfirmation + "</p> \n\n\n" +
                "<p> Si crees que fue un error por favor omita el mensaje. </p>" +
                "<p> Gracias! </p>";
    }

    private String createBodyResetPassword(User userData, String urlResetPassword) {
        return "<h2 style=\"color: #2E86C1;\">" + "Gracias por verificar su cuenta!" + "</h2>"
                + "<div style= \"font-size: 14px;\"> " +
                "<p> Estos son sus datos de acceso a su cuenta. El siguiente paso es cambiar su contraseña: "
                + "Por favor cámbiela haciendo click en el siguiente enlace: <br>"
                + "- " + urlResetPassword + "</p> \n\n\n"
                + "</div> "
                + "<ul>" +
                "   <li> Nombre: <strong>" + userData.getName() + "</strong></li>" +
                "   <li> Username: <strong>" + userData.getUsername() + "</strong></li>" +
                "  </ul>\n\n"
                + "<p>Gracias</p>";
    }

    private String createBodyAlertChangePassword(User userData) {
        return "<p style=\"color: #515A5A\"> Hola </p>"
                + "<h1 style=\"color: #2E86C1;\">" + userData.getName() + "</h1>"
                + "<p> <strong> [ALERTA] </strong>, Se ha cambiado la contraseña de acceso a su cuenta!</p>\n\n"
                + "<p> Por favor revise su cuenta y verifique los cambios</p>"
                + "<p> <strong> NOTA: </strong> Si no reconoce estos cambios por favor cambie su clave o contactese" +
                "       con el administrador de sistemas.</p>\n\n"
                + "<p>Gracias</p>";
    }
}
