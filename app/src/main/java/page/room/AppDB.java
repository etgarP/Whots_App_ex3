package page.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import page.TypeConverter.LastMessageTypeConverter;
import page.TypeConverter.UserTypeConverter;
import page.chat.entities.Contact;

@Database(entities = {Contact.class}, version = 1)
@TypeConverters({LastMessageTypeConverter.class, UserTypeConverter.class})
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}