package com.application.app.service.notification;


import com.application.app.domain.security.User;

public interface NotificationService {

    void sendEmailAccountConfirmation(User userData, String urlConfirmation);

    void sendEmailResetPassword(User userData, String urlResetPassword);

    void sendAlertEmailChangePassword(User userData);

}
