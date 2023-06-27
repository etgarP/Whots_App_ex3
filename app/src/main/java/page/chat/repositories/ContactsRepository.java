package page.chat.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import page.chat.api.ContactAPI;
import page.chat.entities.Contact;
import page.room.AppDB;
import page.room.ContactDao;

public class ContactsRepository {
    private ContactDao dao;
    private ContactListData contactListData;
    private ContactAPI api;
    private String token, firebaseToken;

    public ContactsRepository(Context context, String url) {
        AppDB db = Room.databaseBuilder(context,
                        AppDB.class, "ContactsDB")
                .build();

        dao = db.contactDao();
        contactListData = new ContactListData();
        api = new ContactAPI(dao, url);
        this.token = null;
    }

    public void setToken(String token, String firebaseToken) {
        this.token = token;
        this.firebaseToken = firebaseToken;
        api.get(contactListData, token, firebaseToken);
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            new Thread(() -> {
                List<Contact> contactList = dao.index();
                postValue(contactList);
            }).start();
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                if (token != null)
                    api.get(this, token, firebaseToken);
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }
    // TODO see if these needs adding farther down the line
//    public void add (final Contact contact) { api.add(contact); }
//    public void delete (final Contact contact) { api.delete(contact); }
    public void reload() {
        if (token != null)
            api.get(contactListData, token, firebaseToken);
    }
    public void deleteDataMain() {
        dao.deleteAllData();
    }
}
