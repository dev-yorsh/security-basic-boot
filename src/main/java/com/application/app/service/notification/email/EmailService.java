package com.application.app.service.notification.email;

import com.application.app.service.notification.email.model.EmailDetail;

public interface EmailService {

    void sendSimpleEmail(EmailDetail userRequest);
    void sendHtmlEmail(EmailDetail userRequest);

}
