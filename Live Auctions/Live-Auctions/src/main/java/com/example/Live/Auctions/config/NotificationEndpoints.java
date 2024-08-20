package com.example.Live.Auctions.config;

// import javax.websocket.Session;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// @ServerEndpoint("/topic/socket/newPost")
public class NotificationEndpoints {
    public static final String NEW_POST_ADDED="/topic/socket/newPost";

    /*private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Handle incoming messages, if needed
    }

    public static void sendNotificationToAll(String notification) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(notification);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Other methods and logic*/
}

