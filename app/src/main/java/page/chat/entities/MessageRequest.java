package page.chat.entities;

import com.google.gson.annotations.SerializedName;

public class MessageRequest {
    @SerializedName("msg")
    private String message;

    public MessageRequest(String message) {
        this.message = message;
    }
    
    // Getter and Setter for the 'message' field (optional)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}