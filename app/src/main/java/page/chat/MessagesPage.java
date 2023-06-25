package page.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whotsapp.R;

import page.chat.adapters.MessagesListAdapter;
import page.chat.api.MessageAPI;
import page.chat.entities.User;
import page.chat.viewmodels.MessagesViewModel;
import page.sign_in.SignInAPI;
import page.sign_in.entities.UserPass;

public class MessagesPage extends AppCompatActivity {

    private MessagesViewModel viewModel;
    RecyclerView recyclerView;
    MessagesListAdapter adapter;
    User user;
    public void setScroll() {
        int lastPosition = adapter.getItemCount() - 1;
        recyclerView.scrollToPosition(lastPosition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        //arguments from contactPage
        this.user = intent.getParcelableExtra("User");
        Integer id = intent.getIntExtra("id", -1);
        String url = intent.getStringExtra("url");
        UserPass userPass = intent.getParcelableExtra("userPass");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);

        //set display name
        TextView displayNameTextView = findViewById(R.id.displayNamePlace);
        String displayName = user.getDisplayName();
        displayNameTextView.setText(displayName);

        //set profile picture
        ImageView profilePictureImageView = findViewById(R.id.pfp);
        profilePictureImageView.setImageBitmap(user.getProfilePicBit());

        recyclerView = findViewById(R.id.lstMessages);

        adapter = new MessagesListAdapter(this, user.getUsername());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new MessagesViewModel(getApplicationContext(), url, id);

        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
            SwipeRefreshLayout rfr = findViewById(R.id.refreshLayout);
            rfr.setRefreshing(false);
        });
        viewModel.get().observe(this, messages -> adapter.setMessages(messages));

        // getting token
        MutableLiveData<String> token = new MutableLiveData<>();
        SignInAPI api = new SignInAPI(url);
        api.getToken(token, userPass, null);
        token.observeForever(tokenString -> {
            if (tokenString != null) {
                viewModel.setToken(tokenString);
            }
        });

        //set scroll to always start on bottom
        viewModel.get().observe(this, data -> setScroll());

        //go back to ContactsPage
        findViewById(R.id.backMessages).setOnClickListener(v -> finish());

        //send message
        findViewById(R.id.sendMessageButton).setOnClickListener(v -> {
            EditText et = findViewById(R.id.editTextMessage);
            final String content = et.getText().toString().trim();
            if(content.length()>0){
                MessageAPI messageAPI = new MessageAPI(null,url);
                MutableLiveData<String> done = new MutableLiveData<>();
                messageAPI.add(token.getValue(),id,content, done);
                done.observe(this, string -> {
                    if(string.equals("done")) {
                        et.setHint("Message");
                        et.setText("");
                        viewModel.reload();
                    } else if (string.equals("No internet connection")) {
                        et.setHint(string);
                    }
                });
            }
        });
    }
}