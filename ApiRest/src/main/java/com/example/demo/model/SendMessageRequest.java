package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;


public class SendMessageRequest {

    @JsonProperty
    @Valid
    @NotNull(message = "Type must not be null.")
    private String type;

    @JsonProperty
    @Valid
    @NotNull(message = "UserId must not be null.")
    private String userId;

    @JsonProperty
    @Valid
    @NotNull(message = "Message must not be null.")
    private String message;


    public SendMessageRequest(String type, String userId, String message) {
        this.type = type;
        this.userId = userId;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
