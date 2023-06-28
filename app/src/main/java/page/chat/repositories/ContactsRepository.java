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
// talks to the dao and server api about contacts
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
    // sets the token when it arrives
    public void setToken(String token, String firebaseToken) {
        this.token = token;
        this.firebaseToken = firebaseToken;
        api.get(contactListData, token, firebaseToken);
    }
    // saves the live data of the contacts
    class ContactListData extends MutableLiveData<List<Contact>> {
        /// gets the contacts from the dao
        public ContactListData() {
            super();
            new Thread(() -> {
                List<Contact> contactList = dao.index();
                postValue(contactList);
            }).start();
        }
        // gets the contacts from the api if we got the token from it
        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                if (token != null)
                    api.get(this, token, firebaseToken);
            }).start();
        }
    }
    // returns the live data
    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }
    // reloads the messages
    public void reload() {
        if (token != null)
            api.get(contactListData, token, firebaseToken);
    }
    // deletes data
    public void deleteDataMain() {
        dao.deleteAllData();
    }
}
