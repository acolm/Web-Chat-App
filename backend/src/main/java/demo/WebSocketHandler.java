package demo;

import Message.MessageDTO;
import Processors.AddMessageProcessor;
import Responses.AddMessageResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import spark.Request;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@WebSocket
public class WebSocketHandler {
  // Store sessions if you want to, for example, broadcast a message to all users
  static Map<Session, Session> sessionMap = new ConcurrentHashMap<>();
  Gson gson = new GsonBuilder().create();

  public static void broadcast(String message) {
    sessionMap.keySet().forEach(session -> {
      // loop over sessions
      try {
        session.getRemote().sendString(message);
      }catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    System.out.println("A client has connected");
    sessionMap.put(session, session);
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    System.out.println("A client has disconnected");
    sessionMap.remove(session);
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    MessageDTO messageDTO = gson.fromJson(message,MessageDTO.class);
    AddMessageProcessor addMessageProcessor = new AddMessageProcessor();
    AddMessageResponse res = addMessageProcessor.process(messageDTO);
    if (res.isSuccess()) {
      broadcast(messageDTO.getMessageTime() + "--" + messageDTO.getUser() + ": " + messageDTO.getMessage() + "   | Likes: " + messageDTO.getNumLikes() );
    }
  }
}