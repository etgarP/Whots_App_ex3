package page.chat.viewmodels;

import androidx.lifecycle.LiveData;

import java.util.List;

import page.chat.entities.Message;

public class MessagesViewModel {
//    private MessagesRepository mRepository;
    private LiveData<List<Message>> messages;
//    public ContactsViewModel() {
//        this.mRepository = new MessagesRepository();
//        this.messages = this.mRepository.getAll();
//    }
    public LiveData<List<Message>> get() { return this.messages; }
//    public void add(Message message) { this.mRepository.add(message); }
//    public void delete(Message message) { this.mRepository.delete(message); }
//    public void reload() { this.mRepository.reload(); }
}
