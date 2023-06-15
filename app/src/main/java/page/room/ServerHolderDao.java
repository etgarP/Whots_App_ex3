package page.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import page.ServerStringHolder;

@Dao
public interface ServerHolderDao {
    @Query("SELECT * FROM ServerStringHolder WHERE id = :id")
    ServerStringHolder get(int id);
    @Update
    void update(ServerStringHolder... serverStringHolder);
    @Insert
    void insert(ServerStringHolder... serverStringHolder);
}
