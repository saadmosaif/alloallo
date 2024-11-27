package com.alloallo.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String user = session.getId();
        sessions.put(user, session);
        System.out.println("Connected: " + user);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Message received: " + payload);

        // Broadcast message to all connected sessions
        for (WebSocketSession webSocketSession : sessions.values()) {
            if (webSocketSession.isOpen() && !webSocketSession.getId().equals(session.getId())) {
                webSocketSession.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session.getId());
        System.out.println("Disconnected: " + session.getId());
    }
}
