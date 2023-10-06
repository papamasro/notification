package com.example.demo.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Gateway {

    /* already implemented */

    public void send(String userId, String message) {

        System.out.println("sending message to user " + userId);

    }
}