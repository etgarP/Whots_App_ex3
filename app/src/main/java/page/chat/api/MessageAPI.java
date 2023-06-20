package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import page.WebServiceAPI;
import page.chat.entities.Message;
import page.chat.entities.Messages;
import page.room.MessagesDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private MessagesDao dao;

    public MessageAPI(MessagesDao dao, String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.dao = dao;
    }

    public void get(MutableLiveData<List<Message>> messagesList, String bearerToken, int id) {//todo remove Messages messages
        if (dao == null) return;
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Message>> call = webServiceAPI.getChatMessages(authorizationHeader, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()) {
                    List<Message> oldList = messagesList.getValue();
                    List<Message> ml = response.body();
                    messagesList.postValue(ml);
                    Messages messages = dao.get(id);
                    if (messages == null) {
                        Messages messages1 = new Messages(id, ml);
                        dao.insert(messages1);
                    } else {
                        messages.setMessageList(ml);
                        dao.update(messages);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }
}