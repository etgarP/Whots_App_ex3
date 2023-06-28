package page.chat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

// stores a message
@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("created")
    private String created;

    @SerializedName("sender")
    private User sender;

    @SerializedName("content")
    private String content;

    public Message(String created, User sender, String content) {
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // these are needed for contains in a list
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message otherMessage = (Message) obj;
        return id == otherMessage.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
