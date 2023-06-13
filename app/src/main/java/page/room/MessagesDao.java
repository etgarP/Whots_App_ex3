package page.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import page.chat.entities.Messages;

@Dao
public interface MessagesDao {
    @Query("SELECT * FROM Messages WHERE id = :id")
    Messages get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIfNotExists(Messages messages);

    @Delete
    void delete(Messages messages);
}