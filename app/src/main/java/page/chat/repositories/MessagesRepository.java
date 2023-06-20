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
import page.room.MessageDao;
import page.room.MessageDao;

public class MessagesRepository {
    private MessageDao dao;
    private MessageListData messageListData;
    private MessageAPI api;
    private String token;
    private int id;
    private String url;
    public MessagesRepository(Context context, String url, int id) { //todo fix
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "MessagesDB" + id).build();

        this.id=id;
        this.dao = db.messageDao();
        this.messageListData = new MessageListData();
        this.api = new MessageAPI(this.dao, url);
        this.token = null;
    }
    public void setToken(String token) {
        this.token = token;
        api.get(messageListData, token, id);
    }
    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData(){
            super();
            new Thread(() -> {
                List<Message> messages = dao.index();
                if (messages!=null){
                    postValue(messages);
                }
            }).start();
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                List<Message> messages = dao.index();
                if(messages!=null){
                    postValue(messages);
                }
                if(token!=null){
                    api.get(this,token, id);
                }
            }).start();
        }
    }


public LiveData<List<Message>> getAll() { return this.messageListData; }

    public void reload() {
        if (token!=null){
            api.get(messageListData, token, id);
        }
    }
}
