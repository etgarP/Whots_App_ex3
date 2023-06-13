package page.chat.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import page.chat.api.MessageAPI;
import page.chat.entities.Message;
import page.chat.entities.Messages;
import page.room.AppDB;
import page.room.MessagesDao;

public class MessagesRepository {
    private MessagesDao dao;
    private MessageListData messageListData;
    private MessageAPI api;
    public MessagesRepository(Context context) {
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "MessagesDB").allowMainThreadQueries().build();

        this.dao = db.messageDao();
        this.messageListData = new MessagesRepository.MessageListData();
        this.api = new MessageAPI(this.dao);
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData(){
            super();
            new Thread(() -> {
                int id = 1;//todo delete
                Messages messages= dao.get(id);
                postValue(messages.getMessageList());
            }).start();
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                int id = 1;//todo delete
                Messages messages= dao.get(id);
                api.get(messages);
            }).start();
        }
    }


public LiveData<List<Message>> getAll() { return this.messageListData; }

    public void reload() {
        int id = 1;//todo delete
        api.get(dao.get(id));
    }
}
