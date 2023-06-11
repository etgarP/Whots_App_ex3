package page.chat.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.R;

import java.util.List;
import java.util.concurrent.Executors;

import page.MyApplication;
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

    public ContactAPI(ContactDao dao) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.dao = dao;
    }

    public void get(MutableLiveData<List<Contact>> contactsList) {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkZGRkZmNEZGRkZmNCIsImlhdCI6MTY4NjQxNTU1MH0.5MTRJZLDHKaz_ddrub_Sw6Ey-cI2UVaaK8HO5yNIHuY";
        String authorizationHeader = "Bearer " + bearerToken;
        Call<List<Contact>> call = webServiceAPI.getContacts(authorizationHeader);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> lc = response.body();
                contactsList.postValue(response.body());
                if (lc != null) {
                    for (Contact c: lc) {
                        dao.insertIfNotExists(c);
                    }
                    List<Contact> daoList = dao.index();
                    for (Contact c: daoList) {
                        if (!lc.contains(c))
                            dao.delete(c);
                    }
                    daoList = dao.index();
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }
}
