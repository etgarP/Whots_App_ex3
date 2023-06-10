package page.chat.entities;

import com.google.gson.annotations.SerializedName;

public class LastMessage {
    @SerializedName("id")
    private int id;

    @SerializedName("created")
    private String created;

    @SerializedName("content")
    private String content;

    public LastMessage(int id, String created, String content) {
        this.id = id;
        this.created = created;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }
}