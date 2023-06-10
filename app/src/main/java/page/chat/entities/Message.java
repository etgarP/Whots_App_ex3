package page.chat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    private String content, sender;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

