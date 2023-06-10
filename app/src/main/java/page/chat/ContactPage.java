package page.chat;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactPage extends AppCompatActivity {

    List<Contact> contacts;
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

    private List<Contact> generateContacts() {
        List<Contact> posts = new ArrayList<>();

        Contact p1 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p2 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p3 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        Contact p4 = new Contact("p1", "10:00", "11/11", "hello john", R.drawable.icon);
        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
        posts.add(p4);

        RecyclerView rv=new RecyclerView(posts);

        return posts;
    }
}