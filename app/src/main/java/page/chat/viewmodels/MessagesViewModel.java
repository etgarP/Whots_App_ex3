package page.chat.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import page.chat.entities.Message;
import page.chat.repositories.MessagesRepository;

public class MessagesViewModel {
    private MessagesRepository messagesRepository;
    private LiveData<List<Message>> messages;
    public MessagesViewModel(Context context) {
        this.messagesRepository = new MessagesRepository(context);
        this.messages = this.messagesRepository.getAll();
    }
    public LiveData<List<Message>> get() { return this.messages; }
    public void add(Message message) { this.messagesRepository.add(message); }
    public void reload() { this.messagesRepository.reload(); }
}
