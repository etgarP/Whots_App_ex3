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
    private String token;
    private int id;
    private String url;
    public MessagesRepository(Context context, String url, int id) { //todo fix
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "MessagesDB").build();

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
                Messages messages= dao.get(id);
                if (messages!=null){
                    postValue(messages.getMessageList());
                }
            }).start();
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
//                Messages messages= dao.get(id);
//                if(messages!=null){
//                    postValue(messages.getMessageList());
//                }
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
