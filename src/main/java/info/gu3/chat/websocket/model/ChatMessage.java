package info.gu3.chat.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessage {
  public String name;
  public String message;
  public Type type;

  @Getter
  public enum Type{
    Connect, Message
  }
}
