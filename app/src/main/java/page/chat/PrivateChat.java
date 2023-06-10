package page.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.whotsapp.R;

import java.util.ArrayList;
import java.util.List;

import page.chat.entities.Contact;

public class PrivateChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);
    }

    private List<Contact> generateMessages() {
        List<page.chat.entities.Message> messages = new ArrayList<>();

        page.chat.entities.Message p1 = new page.chat.entities.Message("hello john", "1");
        page.chat.entities.Message p2 = new page.chat.entities.Message("hello john", "1");
        page.chat.entities.Message p3 = new page.chat.entities.Message("hello john", "2");
        page.chat.entities.Message p4 = new page.chat.entities.Message("hello john", "2");
        messages.add(p1);
        messages.add(p2);
        messages.add(p3);
        messages.add(p4);

        RecyclerView rv=new RecyclerView(messages);

        return messages;
    }
}