package page.chat.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import page.chat.entities.Message;
import page.chat.repositories.MessagesRepository;
// links the activity with the messages and the repository
public class MessagesViewModel {
    private MessagesRepository messagesRepository;
    private LiveData<List<Message>> messages;
    public MessagesViewModel(Context context, String url, int id) {
        this.messagesRepository = new MessagesRepository(context, url, id);
        this.messages = this.messagesRepository.getAll();
    }
    // sets the token to comunicate with the server
    public void setToken(String token) {
        messagesRepository.setToken(token);
    }
    // returns the data
    public LiveData<List<Message>> get() { return this.messages; }
    // reload the data
    public void reload() { this.messagesRepository.reload(); }
}
