package page.chat;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whotsapp.databinding.ActivityPrivateChatBinding;

import java.util.ArrayList;
import java.util.List;

import page.chat.adapters.MessageAdapter;
import page.chat.entities.Message;

public class PrivateChat extends AppCompatActivity {
    List<page.chat.entities.Message> messages;
    private ActivityPrivateChatBinding binding;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_private_chat);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityPrivateChatBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        ListView lstFeed = this.binding.messagesListView;

        this.messages = generateMessages();
        final MessageAdapter contactAdapter = new MessageAdapter(this.messages);
        lstFeed.setAdapter(contactAdapter);
//        lstFeed.setOnItemClickListener((parent, view, position, id) -> {
//            Contact p = contacts.get(position);
//            p.select();
//            contactAdapter.notifyDataSetChanged();
//        });
    }
    private List<Message> generateMessages() {
        List<page.chat.entities.Message> messages2 = new ArrayList<>();

        page.chat.entities.Message p1 = new page.chat.entities.Message("hello chad", "Chad");
        page.chat.entities.Message p2 = new page.chat.entities.Message("hi chad", "Chad");
        page.chat.entities.Message p3 = new page.chat.entities.Message("shalom chad", "Chad");
        page.chat.entities.Message p4 = new page.chat.entities.Message("hey chad", "Chad");
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);
        messages2.add(p1);
        messages2.add(p2);
        messages2.add(p3);
        messages2.add(p4);


        return messages2;
    }
}