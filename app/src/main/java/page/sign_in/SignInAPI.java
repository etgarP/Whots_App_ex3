package page.sign_in;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import page.WebServiceAPI;
import page.chat.entities.User;
import page.sign_in.entities.UserPass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public SignInAPI(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
    public void PostUser(UserPass userPass) {

    }
    public void getToken(MutableLiveData<String> token, UserPass userPass) {
        Call<ResponseBody> call = webServiceAPI.createToken(userPass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    try {
                        String s = responseBody.string();
                        token.postValue(s);
                        // Process the response body string
                    } catch (IOException e) {
                        // Handle IO exception
                    } finally {
                        responseBody.close();
                    }
                    // Process the token
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
            }
        });
    }
    public void getUserPassName(String token, String username, MutableLiveData<User> userSaver) {
        String authorizationHeader = "Bearer " + token;
        Call<User> call = webServiceAPI.getUser(authorizationHeader, username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                userSaver.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {}
        });

    }
}