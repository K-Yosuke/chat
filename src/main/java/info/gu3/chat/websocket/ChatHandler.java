package info.gu3.chat.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ChatHandler extends AbstractWebSocketHandler {

  private Map<String, WebSocketSession> _sessions = new HashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    System.out.println("Session Connected = " + session.getId());
    this._sessions.put(session.getId(), session);
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    System.out.println(message.getPayload());

    this._sessions.forEach((id, s) -> {
      try {
        s.sendMessage(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
    String rcv = new String(message.getPayload().array(), StandardCharsets.UTF_8);
    System.out.println(rcv);

    this._sessions.forEach((id, s) -> {
      try {
        s.sendMessage(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    System.out.println("Session Disconnected = " + session.getId());
    this._sessions.remove(session.getId());
  }
}
