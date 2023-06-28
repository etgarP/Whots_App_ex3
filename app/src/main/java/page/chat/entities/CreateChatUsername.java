package page.chat.entities;

import com.google.gson.annotations.SerializedName;
// helps transfer the username in the api
public class CreateChatUsername {
    @SerializedName("username")
    private String username;

    public CreateChatUsername(String username) {
        this.username = username;
    }
}