package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public class SendMessageRequest {
    @JsonProperty
    @Valid
    @NotNull(message = "Message must not be null.")
    private String message;

    public String getMessage() {
        return message;
    }
}
