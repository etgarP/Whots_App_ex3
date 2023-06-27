package page.chat.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Base64;

@Entity
public class User implements Parcelable {
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

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Bitmap getProfilePicBit() {
        if (profilePic.startsWith("data:image/png;base64,") || profilePic.startsWith("data:image/jpg;base64,")) {
            profilePic = profilePic.substring(profilePic.indexOf(',') + 1);
        }
        String base64Data = profilePic.replaceAll("\\s", ""); // Remove whitespace
        base64Data = base64Data.replaceAll("\\n", ""); // Remove line breaks
        byte[] bytes = Base64.getDecoder().decode(base64Data);
        Bitmap picture = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return picture;
    }
    // Parcelable implementation
    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        displayName = in.readString();
        profilePic = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(displayName);
        dest.writeString(profilePic);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}