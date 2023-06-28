package page.sign_in.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import page.chat.entities.User;
// saves the user and userpass
@Entity
public class UserSignedSaver {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("savedUser")
    private User savedUser;
    private UserPass userPass;

    public UserSignedSaver(int id, User savedUser, UserPass userPass) {
        this.id = id;
        this.savedUser = savedUser;
        this.userPass = userPass;
    }

    public int getId() {
        return id;
    }

    public UserPass getUserPass() {
        return userPass;
    }

    public void setUserPass(UserPass userPass) {
        this.userPass = userPass;
    }

    public User getSavedUser() {
        return savedUser;
    }

    public void setId(int id) {
        this.id = id;
    }
}
