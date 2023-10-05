package com.example.demo.controller;

import com.example.demo.model.SendMessageRequest;
import com.example.demo.model.SendMessageResponse;
import com.example.demo.service.Messenger;
import com.example.demo.service.impl.MessengerService;
import com.example.demo.util.DateFormatter;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Validated
public class MessengerController {
    private final Messenger messengerService;
    private final Gson gson = new Gson();

    @Autowired
    public MessengerController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestHeader("UserId") String userId,  @Valid @RequestBody SendMessageRequest request) {
        String serviceResponse = messengerService.sendMessenge(userId, request.getMessage());
        SendMessageResponse response = new SendMessageResponse(DateFormatter.getTimeStamp(), request.getMessage(), gson.toJson(serviceResponse));
        return ResponseEntity.ok(response);
    }

}
