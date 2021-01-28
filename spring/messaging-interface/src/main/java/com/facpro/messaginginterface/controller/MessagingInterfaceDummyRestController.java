package com.facpro.messaginginterface.controller;

import com.facpro.messaginginterface.pojo.PlainOldMessagingObject;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-messaging-interface")
public class MessagingInterfaceDummyRestController {
    
    @PostMapping("/send-default")
    public void sendNewMessageToDefaultDestination(@Validated @RequestBody PlainOldMessagingObject pojoObject) {
        
    }
}
