package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import page.WebServiceAPI;
import page.chat.entities.Contact;
import page.room.ContactDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void get(MutableLiveData<List<Contact>> contactsList, String bearerToken) {
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Contact>> call = webServiceAPI.getContacts(authorizationHeader);
        call.enqueue(new Callback<List<Contact>>() {
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
}
