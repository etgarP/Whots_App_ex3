package page.chat;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.ArrayList;
import java.util.List;

import page.chat.entities.Contact;

public class ContactPage extends AppCompatActivity {

    List<page.chat.entities.Contact> contacts;
    private ActivityContactPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView lstFeed = binding.lstView;

        contacts = generateContacts();
        final ContactAdapter contactAdapter = new ContactAdapter(contacts);
        lstFeed.setAdapter(contactAdapter);
//        lstFeed.setOnItemClickListener((parent, view, position, id) -> {
//            Contact p = contacts.get(position);
//            p.select();
//            contactAdapter.notifyDataSetChanged();
//        });
    }

    private List<page.chat.entities.Contact> generateContacts() {
        List<page.chat.entities.Contact> posts = new ArrayList<>();

        page.chat.entities.Contact p1 = new page.chat.entities.Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        page.chat.entities.Contact p2 = new page.chat.entities.Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        page.chat.entities.Contact p3 = new page.chat.entities.Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        page.chat.entities.Contact p4 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
        posts.add(p4);

        return posts;
    }
}