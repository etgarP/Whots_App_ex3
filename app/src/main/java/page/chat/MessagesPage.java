package page.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityMessagesBinding;

import page.chat.adapters.MessagesListAdapter;
import page.chat.viewmodels.MessagesViewModel;

public class MessagesPage extends AppCompatActivity {

    private MessagesViewModel viewModel;
    private ActivityMessagesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_messages);

        RecyclerView recyclerView = findViewById(R.id.lstMessages);

        final MessagesListAdapter adapter = new MessagesListAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        viewModel = new MessagesViewModel(getApplicationContext());

        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
        });
        viewModel.get().observe(this, messages -> {
            adapter.setMessages(messages);
        });

//        List<Message> lstMessages = new ArrayList<>();
//        User user = new User("Hemi", "hemi hemi 34", "1");
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