package com.example.demo.service.impl;

import com.example.demo.service.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessengerService implements Messenger {
    private static final Logger logger = LoggerFactory.getLogger(MessengerService.class);

    public String sendMessenge(String userId, String message) {
        logger.info("sending message to user " + userId);
        String response = "sending message to user " + userId;
        logger.info("Message sent with status 200 to user " + userId);
        return response;
    }


}
