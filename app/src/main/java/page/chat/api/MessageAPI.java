package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.R;

import java.util.List;
import java.util.concurrent.Executors;

import page.MyApplication;
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

    public MessageAPI(MessagesDao dao) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.dao = dao;
    }

    public void get(MutableLiveData<Messages> messages) {
        //todo put token and delete
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkZGRkZmNEZGRkZmNCIsImlhdCI6MTY4NjQxNTU1MH0.5MTRJZLDHKaz_ddrub_Sw6Ey-cI2UVaaK8HO5yNIHuY";
        String authorizationHeader = "Bearer " + bearerToken;
        //todo put id
        int id = 1; //todo delete
        Call<List<Message>> call = webServiceAPI.getChatMessages(authorizationHeader, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messagesList = response.body();
                Messages messagesObject = new Messages(id, messagesList);
                messages.postValue(messagesObject);
                if (messagesList != null) {
                    dao.insertIfNotExists(messagesObject);
                    for (Message message : messagesList) {
                        dao.insertIfNotExists(message);
                    }
                }
                Messages daoList = dao.get(id);
                for (Message message : daoList.getMessageList()) {
                    if (!messagesList.contains(message)) {
                        dao.delete(message);
                    }
                }
                daoList = dao.get(id);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }

    public void add(Message message){
        //todo add implementation
    }
}