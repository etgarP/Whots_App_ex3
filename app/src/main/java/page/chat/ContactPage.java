package page.chat;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.ArrayList;
import java.util.List;

import page.chat.adapters.ContactsListAdapter;
import page.chat.entities.Contact;
import page.chat.entities.LastMessage;
import page.chat.entities.User;

public class ContactPage extends AppCompatActivity {

    List<Contact> contacts;
    private ActivityContactPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contacts = generateContacts();
        RecyclerView lstPosts = binding.lstPosts;
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);
    }

    private List<Contact> generateContacts() {
        List<Contact> contacts = new ArrayList<>();
        User user = new User("hemi_hemi", "hemi hemi", R.drawable.icon);
        LastMessage lastMessage = new LastMessage(1, "10:00", "Hello world!");
        Contact contact = new Contact(user, lastMessage);
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        contacts.add(new Contact(user, lastMessage));
        return contacts;
    }
}