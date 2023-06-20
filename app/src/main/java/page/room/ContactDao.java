package page.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import page.chat.entities.Contact;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Transaction
    default void insertIfNotExists(Contact contact) {
        if (get(contact.getId()) == null) {
            insert(contact);
        }
    }
    @Query("DELETE FROM contact")
    void deleteAllData();
}