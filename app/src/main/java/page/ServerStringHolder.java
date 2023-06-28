package page;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
// holds the entered server address
@Entity
public class ServerStringHolder {
    @PrimaryKey
    @SerializedName("id")
    int id;
    @SerializedName("serverAddress")
    String serverAddress;

    public ServerStringHolder(String serverAddress) {
        this.id = 1;
        this.serverAddress = serverAddress;
    }

    public int getId() {
        return id;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
