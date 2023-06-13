package page.TypeConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import page.chat.entities.Message;

public class MessageListTypeConverter {
    @TypeConverter
    public static List<Message> fromString(String value) {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Message> list) {
        return new Gson().toJson(list);
    }
}
