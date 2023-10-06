package com.example.demo.service;


import com.example.demo.service.impl.Gateway;
import com.example.demo.service.impl.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private NotificationService notificationService;

    @Mock
    private Gateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationService(gateway);
    }

    @Test
    void testSendMessenge_ShouldSendNotification() {
        String userId = "user123";
        String message = "Test message";

        String response = notificationService.sendMessenge(userId, message);

        verify(gateway, times(1)).send(userId, message);

    }
}