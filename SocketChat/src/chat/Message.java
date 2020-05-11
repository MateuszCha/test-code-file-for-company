package chat;

import java.io.Serializable;

public class Message implements Serializable {
    private final String data;
    private final MessageType type;

    public Message(MessageType type){
        this.type = type;
        this.data= null;
    }
    public Message(MessageType type, String data){
        this.type = type;
        this.data = data;
    }

    public String getData() {
        return data;
    }
    public MessageType getType() {
        return type;
    }
}

