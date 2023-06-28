package page.sign_in.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import page.TypeConverter.UserPassTypeConverter;
// has the username and password
@Entity
@TypeConverters(UserPassTypeConverter.class)
public class UserPass implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public UserPass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected UserPass(Parcel in) {
        id = in.readInt();
        username = in.readString();
        password = in.readString();
    }
    // makes this parcerable to move it to another intent
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(password);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserPass> CREATOR = new Creator<UserPass>() {
        @Override
        public UserPass createFromParcel(Parcel in) {
            return new UserPass(in);
        }

        @Override
        public UserPass[] newArray(int size) {
            return new UserPass[size];
        }
    };
}
