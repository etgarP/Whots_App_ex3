package page.sign_in.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import page.TypeConverter.UserPassTypeConverter;

@Entity
@TypeConverters(UserPassTypeConverter.class)
public class UserPass {
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
}
