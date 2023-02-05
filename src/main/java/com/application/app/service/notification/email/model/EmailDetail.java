package com.application.app.service.notification.email.model;

import lombok.Data;

@Data
public class EmailDetail {

    private String subject;
    private String sender;
    private String to;
    private String body;
}
