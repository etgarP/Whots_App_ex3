package page.chat;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.List;

import page.chat.adapters.ContactsListAdapter;
import page.chat.api.ContactAPI;
import page.chat.entities.Contact;

public class ContactPage extends AppCompatActivity {

    List<Contact> contacts;
    private ActivityContactPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ContactAPI contactApi = new ContactAPI();
        contactApi.get();
        RecyclerView lstPosts = binding.lstPosts;
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);
    }
}