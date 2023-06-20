package page.chat.entities;

import com.google.gson.annotations.SerializedName;

public class CreateChatUsername {
    @SerializedName("username")
    private String username;

    public CreateChatUsername(String username) {
        this.username = username;
    }
}