package page.chat.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Base64;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("profilePic")
    private String profilePic;

    public User(String username, String displayName, String profilePic) {
        this.username = username;
        this.displayName = displayName;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public Bitmap getProfilePicBit() {
       String base64Data = profilePic.substring(profilePic.indexOf(',') + 1);
       byte[] bytes = Base64.getDecoder().decode(base64Data);
       Bitmap picture = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
       return picture;
    }
}