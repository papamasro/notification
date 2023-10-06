package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class SendMessageRequest {
    @JsonProperty
    @Valid
    @NotBlank(message = "Message must not be empty.")
    @NotNull(message = "Message must not be null.")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
