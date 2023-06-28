package page.chat;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import page.ServerStringHolder;
import page.room.AppDB;
import page.room.ServerHolderDao;
// gets and saves an entered server ip in dao
public class ServerStringRepository {
    private ServerHolderDao dao;
    public ServerStringRepository(Context context) {
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "ServerStringDB")
                .build();

        dao = db.serverHolderDao();
    }
    // gets the string
    public void get(MutableLiveData<String> mutableLiveData, MutableLiveData<Integer> whichCall) {
        new Thread(() -> {
            ServerStringHolder ssh = dao.get(1);
            if (ssh != null){
                mutableLiveData.postValue(ssh.getServerAddress());
            }
            whichCall.postValue(2);
        }).start();
    }
    // different get
    public void get(MutableLiveData<ServerStringHolder> mutableLiveData) {
        new Thread(() -> {
            ServerStringHolder ssh = dao.get(1);
            mutableLiveData.postValue(ssh);
        }).start();
    }
    // update the string
    public void set(String server) {
        new Thread(() -> {
            dao.update(new ServerStringHolder(server));
        }).start();
    }
    // inserts the ip
    public void insert(String server) {
        new Thread(() -> {
            dao.insert(new ServerStringHolder(server));
        }).start();
    }
}