package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.R;

import java.util.List;

import page.MyApplication;
import page.chat.entities.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.webServiceAPI = this.retrofit.create(WebServiceAPI.class);
    }

    public void get(MutableLiveData<List<Message>> messagesList) {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkZGRkZmNEZGRkZmNCIsImlhdCI6MTY4NjQxNTU1MH0.5MTRJZLDHKaz_ddrub_Sw6Ey-cI2UVaaK8HO5yNIHuY";
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Message>> call = this.webServiceAPI.getMessages(authorizationHeader);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> lm = response.body();
                messagesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }
}