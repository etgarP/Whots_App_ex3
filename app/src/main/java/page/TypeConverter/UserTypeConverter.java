package page.TypeConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import page.chat.entities.User;
// converts User to gson for dao
public class UserTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static User fromString(String value) {
        return gson.fromJson(value, User.class);
    }

    @TypeConverter
    public static String toString(User user) {
        return gson.toJson(user);
    }
}
