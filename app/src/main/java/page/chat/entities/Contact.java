package page.chat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName, hour, date, lastMessage;
    private int profileImage;

    public Contact(String displayName, String hour, String date, String lastMessage, int profileImage) {
        this.displayName = displayName;
        this.hour = hour;
        this.date = date;
        this.lastMessage = lastMessage;
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    // todo: make it so it returns the hour if its today and the date if it isnt
    public String getWhen() {
        return hour;
    }

    public String getLastMessage() {
        return lastMessage;
    }
    public int getProfileImage() {
        return profileImage;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
}