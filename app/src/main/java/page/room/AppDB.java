package page.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import page.TypeConverter.DataConverter;
import page.TypeConverter.LastMessageTypeConverter;
import page.TypeConverter.UserTypeConverter;
import page.chat.entities.Contact;
import page.chat.entities.Message;
import page.chat.entities.Messages;


@Database(entities = {Contact.class, Messages.class}, version = 4)
@TypeConverters({LastMessageTypeConverter.class, UserTypeConverter.class, DataConverter.class})
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();

    public abstract MessagesDao messageDao();
}