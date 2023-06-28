package page.TypeConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import page.sign_in.entities.UserPass;
// converts UserPass to gson for dao
public class UserPassTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static UserPass fromString(String value) {
        return gson.fromJson(value, UserPass.class);
    }

    @TypeConverter
    public static String toString(UserPass userPass) {
        return gson.toJson(userPass);
    }
}
