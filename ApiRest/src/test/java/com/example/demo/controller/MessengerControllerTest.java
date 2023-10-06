package com.example.demo.controller;


import com.example.demo.model.SendMessageRequest;
import com.example.demo.model.SendMessageResponse;
import com.example.demo.service.Messenger;
import com.example.demo.service.impl.MessengerService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessengerControllerTest {

    @Mock
    private MessengerService messengerService;

    private MessengerController messengerController;

    private Gson gson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messengerController = new MessengerController(messengerService);
        gson = new Gson();
    }

    @Test
    void testSendMessage_WithValidRequest_ShouldReturnOkResponse() {
        String userId = "user123";
        SendMessageRequest request = new SendMessageRequest();
        request.setMessage("Test message");
        String serviceResponse = "Message sent successfully";
        when(messengerService.sendMessenge(userId, request.getMessage())).thenReturn(serviceResponse);

        ResponseEntity<SendMessageResponse> responseEntity = messengerController.sendMessage(userId, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        SendMessageResponse response = responseEntity.getBody();
        assertEquals("200", response.getStatus());
        assertEquals(serviceResponse, gson.fromJson(response.getResponse(), String.class));
    }

    @Test
    void testSendMessage_WithInvalidRequest_ShouldReturnBadRequest() {
        String userId = "user123";
        SendMessageRequest request = new SendMessageRequest(); // Missing message
        when(messengerService.sendMessenge(userId, request.getMessage())).thenReturn(null);

        ResponseEntity<SendMessageResponse> responseEntity = messengerController.sendMessage(userId, request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}