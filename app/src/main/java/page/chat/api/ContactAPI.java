package page.chat.api;

import com.example.whotsapp.R;

import java.util.List;

import page.MyApplication;
import page.chat.entities.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    List<Contact> contacts;

    public ContactAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkZGRkZmNEZGRkZmNCIsImlhdCI6MTY4NjQxNTU1MH0.5MTRJZLDHKaz_ddrub_Sw6Ey-cI2UVaaK8HO5yNIHuY";
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Contact>> call = webServiceAPI.getContacts(authorizationHeader);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }
}
