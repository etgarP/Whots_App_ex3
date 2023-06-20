package page.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import page.chat.entities.Messages;

@Dao
public interface MessagesDao {
    @Query("SELECT * FROM Messages WHERE id = :id")
    Messages get(int id);

    @Insert
    void insert(Messages messages);

    @Delete
    void delete(Messages messages);

    @Update
    void update(Messages messages);
}