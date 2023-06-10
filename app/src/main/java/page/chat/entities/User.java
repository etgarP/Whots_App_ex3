package page.chat.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.annotations.SerializedName;

import java.util.Base64;

public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("profilePic")
    private String profilePic;

    private Bitmap picture;

    public User(String username, String displayName, String profilePic) {
        this.username = username;
        this.displayName = displayName;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Bitmap getProfilePic() {
       if (picture == null) {
           String base64Data = profilePic.substring(profilePic.indexOf(',') + 1);
           byte[] bytes = Base64.getDecoder().decode(base64Data);
           picture = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
       }
       return picture;
    }
}