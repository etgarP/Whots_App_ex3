package page;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import page.room.AppDB;
import page.room.WhichModeDao;
// gets and saves the theme were in
public class WhichModeRep {
    private WhichModeDao dao;
    public WhichModeRep(Context context) {
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "WhichModeDB")
                .build();

        dao = db.whichModeDao();
    }
    // gets the mode
    public void get(MutableLiveData<String> mutableLiveData) {
        new Thread(() -> {
            WhichMode mode = dao.get(1);
            if (mode == null) {
                dao.insert(new WhichMode("auto"));
                mutableLiveData.postValue("auto");
            } else {
                mutableLiveData.postValue(mode.getMode());
            }
        }).start();
    }
    // inserts if theres no theme, else updates the theme
    public void update(String mode) {
        new Thread(() -> {
            WhichMode wMode = dao.get(1);
            if (wMode == null) {
                dao.insert(new WhichMode(mode));
            } else {
                dao.update(new WhichMode(mode));
            }
        }).start();
    }
}
