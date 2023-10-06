package com.example.demo.service;
import com.example.demo.service.impl.MessengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MessengerServiceTest {

    private MessengerService messengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messengerService = new MessengerService();
    }

    @Test
    void testSendMessenge_ShouldSendMessageAndLog() {
        String userId = "user123";
        String message = "Test message";

        String response = messengerService.sendMessenge(userId, message);

        assertEquals("sending message to user user123", response);
    }
}