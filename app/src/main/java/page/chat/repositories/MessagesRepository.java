package page.chat.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import page.chat.api.MessageAPI;
import page.chat.entities.Message;
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
//                List<Message> messageList = dao.index();
//                postValue(messageList);
            }).start();
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
//                api.get(this);

            }).start();
        }
    }


public LiveData<List<Message>> getAll() { return this.messageListData; }

    public void add (final Message message){
        api.add(message);
    }

    public void reload() {
//        api.get(messageListData);
    }
}
