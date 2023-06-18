package page.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import page.WhichMode;

@Dao
public interface WhichModeDao {
    @Query("SELECT * FROM WhichMode WHERE id = :id")
    WhichMode get(int id);
    @Update
    void update(WhichMode... WhichMode);
    @Insert
    void insert(WhichMode... WhichMode);
}
