package page.register;

import com.example.whotsapp.R;

import java.util.concurrent.Executors;

import page.MyApplication;
import page.WebServiceAPI;
import page.chat.entities.User;
import page.sign_in.entities.UserPassName;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public RegisterApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
//    @POST("Users")
//    Call<User> createUser(@Body UserPassName userPassName);
    public void postUser(UserPassName ups) {
        Call<User> call = webServiceAPI.createUser(ups);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {}
        });
    }
}