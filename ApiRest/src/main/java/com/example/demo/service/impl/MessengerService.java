package com.example.demo.service.impl;

import com.example.demo.service.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessengerService implements Messenger {

    private static final Logger logger = LoggerFactory.getLogger(MessengerService.class);


    public String sendMessenge(String msg) {
        return "ok";
    }


    class Gateway {

        /* already implemented */

        void send(String userId, String message) {
            logger.info("sending message to user " + userId);

            System.out.println("sending message to user " + userId);

        }

    }
}
