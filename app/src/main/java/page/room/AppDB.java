package page.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import page.TypeConverter.LastMessageTypeConverter;
import page.TypeConverter.UserTypeConverter;
import page.chat.entities.Contact;
import page.chat.entities.Message;
import page.chat.entities.Messages;

@Database(entities = {Contact.class, Messages.class, Message.class}, version = 3)
@TypeConverters({LastMessageTypeConverter.class, UserTypeConverter.class})
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();

    public abstract MessagesDao messageDao();
}