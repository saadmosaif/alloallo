package com.alloallo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketMessage;

@Controller
public class SignalingController {

    @MessageMapping("/signaling")
    @SendTo("/topic/signaling")
    public WebSocketMessage relaySignal(WebSocketMessage message) {
        System.out.println("Relaying signaling message: " + message);
        return message;
    }
}
