package page;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
// saves the theme
@Entity
public class WhichMode {
    @PrimaryKey
    @SerializedName("id")
    int id;
    @SerializedName("mode")
    String mode;

    public WhichMode(String mode) {
        this.id = 1;
        this.mode = mode;
    }

    public int getId() {
        return id;
    }

    public String getMode() {
        return mode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
