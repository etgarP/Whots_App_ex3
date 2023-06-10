package page.chat;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.ArrayList;
import java.util.List;

import page.chat.adapters.ContactsListAdapter;
import page.chat.entities.Contact;

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
        List<Contact> posts = new ArrayList<>();

        Contact p1 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p2 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p3 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p4 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));
        posts.add(new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon));



        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
        posts.add(p4);

        return posts;
    }
}