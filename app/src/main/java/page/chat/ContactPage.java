package page.chat;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.List;

import page.chat.adapters.ContactsListAdapter;
import page.chat.entities.Contact;
import page.chat.entities.User;
import page.chat.viewmodels.ContactsViewModel;

public class ContactPage extends AppCompatActivity {

    List<Contact> contacts;
    private ContactsViewModel viewModel;
    private ActivityContactPageBinding binding;

    private void setDetails() {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        binding.displayNamePlace.setText(user.getDisplayName());
        binding.pfp.setImageBitmap(user.getProfilePicBit());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setDetails();
        viewModel = new ContactsViewModel(getApplicationContext());
        RecyclerView lstPosts = binding.lstPosts;
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(this));
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
        });
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });
        binding.back.setOnClickListener(view -> finish());
    }
}