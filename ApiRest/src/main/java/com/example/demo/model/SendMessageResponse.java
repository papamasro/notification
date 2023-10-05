package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SendMessageResponse {

    private String type;

    private String userId;

    private String message;

    public SendMessageResponse(String type, String userId, String message) {
        this.type = type;
        this.userId = userId;
        this.message = message;
    }
}
