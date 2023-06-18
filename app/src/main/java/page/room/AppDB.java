package page.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import page.ServerStringHolder;
import page.TypeConverter.LastMessageTypeConverter;
import page.TypeConverter.UserPassTypeConverter;
import page.TypeConverter.UserTypeConverter;
import page.WhichMode;
import page.chat.entities.Contact;
import page.sign_in.entities.UserSignedSaver;

@Database(entities = {Contact.class, ServerStringHolder.class, UserSignedSaver.class, WhichMode.class}, version = 4)
@TypeConverters({LastMessageTypeConverter.class, UserTypeConverter.class, UserPassTypeConverter.class})
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract ServerHolderDao serverHolderDao();
    public abstract UserSignedSaverDao userSignedSaverDao();
    public abstract WhichModeDao whichModeDao();
}