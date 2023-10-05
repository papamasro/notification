package com.example.demo.controller;

import com.example.demo.model.SendMessageRequest;
import com.example.demo.model.SendMessageResponse;
import com.example.demo.service.impl.MessengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
@Validated
public class MessengerController {

    @Autowired
    MessengerService messengerService;

    @PostMapping("send")
    @ResponseBody
    public ResponseEntity<SendMessageResponse> sendMessage(@Valid @RequestBody SendMessageRequest request) {
            String serviceResponse = messengerService.sendMessenge(request.getMessage());
            SendMessageResponse response = new SendMessageResponse(request.getType(),request.getMessage(),request.getUserId());
            return ResponseEntity.ok(response);


    }

}
