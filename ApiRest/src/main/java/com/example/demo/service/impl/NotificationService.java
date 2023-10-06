package com.example.demo.service.impl;

import com.example.demo.service.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements Notification {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final Gateway gateway;
    @Autowired
    public NotificationService(Gateway gateway) {
        this.gateway = gateway;
    }

    public String sendMessenge(String userId, String message) {
        logger.info("sending message to user " + userId);
        String response = "sending message to user " + userId;
        gateway.send(userId,message);
        logger.info("Message sent with status 200 to user " + userId);
        return response;
    }


}

