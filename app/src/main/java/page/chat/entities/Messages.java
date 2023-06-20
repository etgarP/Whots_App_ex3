package page.chat.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@Entity
public class Messages {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("messageList")
    private String serializedMessageList;

    public Messages(int id, String serializedMessageList) {
        this.id = id;
        this.serializedMessageList = serializedMessageList;
    }
    @Ignore
    public Messages(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerializedMessageList() {
        return serializedMessageList;
    }

    public List<Message> getMessageList() {
        return deserializeMessageList(serializedMessageList);
    }

    public void setMessageList(List<Message> messageList) {
        this.serializedMessageList = serializeMessageList(messageList);
    }

    private String serializeMessageList(List<Message> messageList) {
        // Convert the list of messages to a serialized string (e.g., JSON or CSV)
        // You can use a library like Gson or Jackson for serialization.
        // Example with Gson:
        return new Gson().toJson(messageList);
    }

    private List<Message> deserializeMessageList(String serializedMessageList) {
        // Convert the serialized string back to a list of messages
        // You need to reverse the serialization process.
        // Example with Gson:
        Type type = new TypeToken<List<Message>>() {}.getType();
        return new Gson().fromJson(serializedMessageList, type);
    }
}
