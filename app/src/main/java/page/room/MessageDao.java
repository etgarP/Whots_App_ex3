package page.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import page.chat.entities.Message;
// room save for messages
@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message WHERE id = :id")
    Message get(int id);
    @Query("SELECT * FROM Message")
    List<Message> index();

    @Insert
    void insert(Message message);
    @Transaction
    default void insertIfNotExists(Message message) {
        if (get(message.getId()) == null) {
            insert(message);
        }
    }

    @Delete
    void delete(Message message);

    @Update
    void update(Message message);
}