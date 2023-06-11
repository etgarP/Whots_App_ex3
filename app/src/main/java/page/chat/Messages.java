package page.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;

import java.util.ArrayList;
import java.util.List;

import page.chat.adapters.MessagesListAdapter;
import page.chat.entities.Message;
import page.chat.entities.User;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        RecyclerView lstMessages = findViewById(R.id.lstMessages);

        final MessagesListAdapter adapter = new MessagesListAdapter(this);
        lstMessages.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        lstMessages.setLayoutManager(layoutManager);

        List<Message> messages = new ArrayList<>();
        User user = new User("Hemi", "hemi hemi 34", "1");
        messages.add(new Message("2",user,"1"));
        messages.add(new Message("2",user,"2"));
        messages.add(new Message("2",user,"3"));
        messages.add(new Message("2",user,"4"));
        messages.add(new Message("2",user,"5"));
        messages.add(new Message("2",user,"6"));
        messages.add(new Message("2",user,"7"));
        messages.add(new Message("2",user,"8"));
        messages.add(new Message("2",user,"9"));





        adapter.setMessages(messages);

    }
}