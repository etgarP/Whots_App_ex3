package page.sign_in;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import page.chat.entities.User;
import page.room.AppDB;
import page.room.UserSignedSaverDao;
import page.sign_in.entities.UserPass;
import page.sign_in.entities.UserSignedSaver;
// getting a signed user
public class UserSignedRepository {
    private UserSignedSaverDao dao;
    public UserSignedRepository(Context context) {
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "UserSignedDB")
                .build();
        dao = db.userSignedSaverDao();
    }
    // getting the user
    public void get(MutableLiveData<UserSignedSaver> mutableLiveData) {
        new Thread(() -> {
            UserSignedSaver ssh = dao.get(1);
            mutableLiveData.postValue(ssh);
        }).start();
    }
    // insert a new signed user
    public void insert(UserPass up, User u) {
        new Thread(() -> {
            dao.insert(new UserSignedSaver(1, u, up));
        }).start();
    }
    // deletes the data in the dao
    public void deleteDataMain() {
        dao.deleteAllData();
    }
}