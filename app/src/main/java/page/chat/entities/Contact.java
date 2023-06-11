package page.chat.entities;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
//    private String displayName, time, lastMessage;
//    private int profileImage;
    @SerializedName("id")
    private int id;

    @SerializedName("user")
    private User user;

    @SerializedName("lastMessage")
    private LastMessage lastMessage;

    public Contact(User user, LastMessage lastMessage) {
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDisplayName() {
        return user.getDisplayName();
    }
    public Bitmap getProfileImage() {
        return user.getProfilePicBit();
    }

    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageText() {
        if (lastMessage != null)
            return lastMessage.getContent();
        return "";
    }
    public String getWhen() {
        if (lastMessage != null) {
            return lastMessage.getTime();
        }
        return "";
    }

    public void setId(int id) {
        this.id = id;
    }
}