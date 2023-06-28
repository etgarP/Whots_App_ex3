package page.chat.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import page.chat.entities.Contact;
import page.chat.repositories.ContactsRepository;
// links the activity with the contacts and the repository
public class ContactsViewModel extends ViewModel {
    private ContactsRepository mRepository;
    private LiveData<List<Contact>> contacts;
    public ContactsViewModel(Context context, String url) {
        mRepository = new ContactsRepository(context, url);
        contacts = mRepository.getAll();
    }
    // sets the token so it can talk to the server
    public void setTokens(String token, String firebaseToken) {
        mRepository.setToken(token, firebaseToken);

    }
    // get the contacts
    public LiveData<List<Contact>> get() {
        return contacts;
    }
    // reloads the contacts
    public void reload() { mRepository.reload(); }
}
