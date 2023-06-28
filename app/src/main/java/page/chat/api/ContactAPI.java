package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import page.WebServiceAPI;
import page.chat.entities.Contact;
import page.chat.entities.CreateChatUsername;
import page.room.ContactDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// gets and posts contacts data to and from the server
public class ContactAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private ContactDao dao;

    public ContactAPI(ContactDao dao, String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.dao = dao;
    }
    // takes in a mutable list, tokens, and get the contact list from the server
    // also the server saves the firebase token
    public void get(MutableLiveData<List<Contact>> contactsList, String bearerToken, String firebaseToken) {
        if (dao == null) return;
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Contact>> call = webServiceAPI.getContacts(firebaseToken, authorizationHeader);
        call.enqueue(new Callback<List<Contact>>() {
            // gets the contact list applies it, saves it in the dao, deletes unneeded from dao
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> oldList = contactsList.getValue();
                List<Contact> lc = response.body();
                contactsList.postValue(response.body());
                if (lc != null) {
                    for (Contact c: lc) {
                        dao.insertIfNotExists(c);
                    }
                    if (oldList != null) {
                        for (Contact c : oldList) {
                            if (!lc.contains(c))
                                dao.delete(c);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }
    // adds a new contact using the token, and a username
    public void addContact(MutableLiveData<String> status, String bearerToken, String username) {
        String authorizationHeader = "Bearer " + bearerToken;
        CreateChatUsername username1 = new CreateChatUsername(username);
        Call<ResponseBody> call = webServiceAPI.createChat(authorizationHeader, username1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    status.postValue("Friend successfully added :)");
                }
                else {
                    status.postValue("Wrong username or already added");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                status.postValue("No connection to the server");
            }
        });
    }
}
