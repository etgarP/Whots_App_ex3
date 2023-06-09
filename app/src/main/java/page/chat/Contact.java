package page.chat;

public class Contact {
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
}