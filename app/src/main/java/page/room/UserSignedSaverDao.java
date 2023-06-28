package page.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import page.sign_in.entities.UserSignedSaver;
// room save for user and userpass that are signed
@Dao
public interface UserSignedSaverDao {
    @Query("SELECT * FROM UserSignedSaver WHERE id = :id")
    UserSignedSaver get(int id);
    @Update
    void update(UserSignedSaver... userSignedSavers);
    @Insert
    void insert(UserSignedSaver... userSignedSavers);
    @Query("DELETE FROM UserSignedSaver")
    void deleteAllData();
}
