package page.chat.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Messages {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @Embedded
    @SerializedName("messageList")
    private List<Message> messageList;

    Messages(){}
    public Messages(int id, List<Message> messagesList) {
        this.id = id;
        this.messageList = messagesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
