package page.TypeConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import page.chat.entities.LastMessage;
// converts last message to gson for dao
public class LastMessageTypeConverter {
    @TypeConverter
    public static String fromLastMessage(LastMessage lastMessage) {
        return new Gson().toJson(lastMessage);
    }

    @TypeConverter
    public static LastMessage toLastMessage(String lastMessageJson) {
        return new Gson().fromJson(lastMessageJson, LastMessage.class);
    }
}
