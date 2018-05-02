package info.gu3.chat.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.gu3.chat.websocket.model.ChatMessage;
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

  public void afterConnectionEstablished(WebSocketSession session) {
    System.out.println("Session Connected = " + session.getId());
    this._sessions.put(session.getId(), session);
  }

  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    System.out.println("handleTextMessage start = " + session.getId());
    System.out.println(message.getPayload());

    ObjectMapper mapper = new ObjectMapper();
    ChatMessage chatMessage = mapper.readValue(message.getPayload(), ChatMessage.class);

    if (chatMessage.type == ChatMessage.Type.Connect) {
      chatMessage.message = "＊＊＊入室しました＊＊＊";
      session.getAttributes().put("name", chatMessage.name);
    }

    this._sessions.forEach((id, s) -> {
      try {
        s.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessage)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
    System.out.println("handleBinaryMessage start = " + session.getId());
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

  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    System.out.println("Session Disconnected = " + session.getId());
    this._sessions.remove(session.getId());

    ObjectMapper mapper = new ObjectMapper();
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.name = (String) session.getAttributes().get("name");
    chatMessage.message = "＊＊＊退室しました＊＊＊";

    this._sessions.forEach((id, s) -> {
      try {
        s.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessage)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
