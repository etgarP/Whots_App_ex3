package page.register;

import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.R;

import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import page.MyApplication;
import page.WebServiceAPI;
import page.sign_in.entities.UserPassName;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public RegisterApi(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
    // creates a new user
    public void createUser(UserPassName ups, MutableLiveData<String> goodPost) {
        Call<ResponseBody> call = webServiceAPI.createUser(ups);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    goodPost.postValue("done");
                }
                // leaves an error if the picture is too big
                else if (response.raw().message().equals("Payload Too Large")) {
                    goodPost.postValue("Image too big");
                } else goodPost.postValue("User already exists");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                goodPost.postValue("No connection");
            }
        });
    }
}