package page.chat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// stores the last message in a contact
@Entity
public class LastMessage {
    @PrimaryKey(autoGenerate = true)
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
    // returns the time if the message was from today, else return the date
    public String getTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        ZonedDateTime currentZonedDateTime = currentDateTime.atZone(ZoneId.of("GMT+3"));
        LocalDate currentDate = currentZonedDateTime.toLocalDate();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(created, formatter);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime gmt3DateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("GMT+3"));

        LocalDate messageDate = gmt3DateTime.toLocalDate();
        LocalTime messageTime = gmt3DateTime.toLocalTime();
        String date = messageDate.toString();
        String time = messageTime.format(timeFormatter);

        if (messageDate.equals(currentDate)) {
            return time;
        } else {
            return date;
        }
    }


    public String getContent() {
        return content;
    }
}