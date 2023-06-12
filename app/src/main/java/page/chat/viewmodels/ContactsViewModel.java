package page.chat.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import page.chat.entities.Contact;
import page.chat.repositories.ContactsRepository;

public class ContactsViewModel extends ViewModel {
    private ContactsRepository mRepository;
    private LiveData<List<Contact>> contacts;
    public ContactsViewModel(Context context) {
        mRepository = new ContactsRepository(context);
        contacts = mRepository.getAll();
    }
    public LiveData<List<Contact>> get() { return contacts; }
//    public void add(Contact contact) { mRepository.add(contact); }
//    public void delete(Contact contact) { mRepository.delete(contact); }
    public void reload() { mRepository.reload(); }
}
