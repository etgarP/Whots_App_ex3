package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import page.WebServiceAPI;
import page.chat.entities.Message;
import page.chat.entities.MessageRequest;
import page.room.MessageDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private MessageDao dao;

    public MessageAPI(MessageDao dao, String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.dao = dao;
    }

    public void add(String bearerToken, int id, String msg, MutableLiveData<String> done){
        String authorizationHeader = "Bearer " + bearerToken;
        MessageRequest messageRequest = new MessageRequest(msg);
        Call<ResponseBody> call = webServiceAPI.sendMessage(authorizationHeader, id, messageRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    done.postValue("done");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                done.postValue("No internet connection");
            }
        });
    }

    public void get(MutableLiveData<List<Message>> messagesList, String bearerToken, int id) {
        if (dao == null) return;
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Message>> call = webServiceAPI.getChatMessages(authorizationHeader, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()) {
                    List<Message> oldList = messagesList.getValue();
                    List<Message> ml = response.body();
                    if (ml != null) {
                        for (Message message: ml) {
                            dao.insertIfNotExists(message);
                        }
                        if (oldList != null) {
                            for (Message message : oldList) {
                                if (!ml.contains(message))
                                    dao.delete(message);
                            }
                        }
                    }
                    List<Message> messages = dao.index();
                    messagesList.postValue(messages);
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }
}