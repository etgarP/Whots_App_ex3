package page.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityMessagesBinding;

import page.chat.adapters.MessagesListAdapter;
import page.chat.entities.User;
import page.chat.viewmodels.MessagesViewModel;
import page.sign_in.SignInAPI;
import page.sign_in.entities.UserPass;

public class MessagesPage extends AppCompatActivity {

    private MessagesViewModel viewModel;
    private ActivityMessagesBinding binding;
    RecyclerView recyclerView;
    MessagesListAdapter adapter;
    public void setScroll() {
        int lastPosition = adapter.getItemCount() - 1;
        recyclerView.scrollToPosition(lastPosition);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("User");
        Integer id = intent.getIntExtra("id", -1);
        String url = intent.getStringExtra("url");
        UserPass userPass = intent.getParcelableExtra("userPass");

        super.onCreate(savedInstanceState);
//        binding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_messages);

        recyclerView = findViewById(R.id.lstMessages);

        adapter = new MessagesListAdapter(this);
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
        viewModel.get().observe(this, messages -> {
            adapter.setMessages(messages);
        });

        // getting token
        MutableLiveData<String> token = new MutableLiveData<>();
        SignInAPI api = new SignInAPI(url);
        api.getToken(token, userPass, null);
        token.observeForever(tokenString -> {
            if (tokenString != null) {
                viewModel.setToken(tokenString);
            }
        });
        viewModel.get().observe(this, data -> {
            setScroll();
        });

//        List<Message> lstMessages = new ArrayList<>();
//        lstMessages.add(new Message("2",user,"1"));
//        lstMessages.add(new Message("2",user,"2"));
//        lstMessages.add(new Message("2",user,"3"));
//        lstMessages.add(new Message("2",user,"4"));
//        lstMessages.add(new Message("2",user,"5"));
//        lstMessages.add(new Message("2",user,"6"));
//        lstMessages.add(new Message("2",user,"7"));
//        lstMessages.add(new Message("2",user,"8"));
//        lstMessages.add(new Message("2",user,"9"));
//        adapter.setMessages(lstMessages);






    }
}