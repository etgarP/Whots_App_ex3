package page.chat.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.LinkedList;
import java.util.List;

import page.chat.api.ContactAPI;
import page.chat.entities.Contact;

public class ContactsRepository {
//    private ContactDao dao;
    private ContactListData contactListData;
    //private ContactAPI api;

    public ContactsRepository() {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.postDao();
        contactListData = new ContactListData();
        //api = new ContactAPI(contactListData, dao);
    }

    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                ContactAPI contactApi = new ContactAPI();
                contactApi.get(this);
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }
    // TODO see if these needs adding farther down the line
//    public void add (final Contact contact) { api.add(contact); }
//    public void delete (final Contact contact) { api.delete(contact); }
    //public void reload() { api.get(); }
}
