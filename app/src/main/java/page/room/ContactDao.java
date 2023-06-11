package page.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import page.chat.entities.Contact;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> index();
// todo: might not need these

//    @Query("SELECT * FROM contact WHERE id = :id")
//    Contact get(int id);
//
//    @Insert
//    void insert(Post... posts);
//
//    @Update
//    void update(Post... posts);
//
//    @Delete
//    void delete(Post... posts);
}
