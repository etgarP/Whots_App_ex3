package page.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityAddPageBinding;

import page.ServerStringHolder;
import page.chat.api.ContactAPI;
import page.sign_in.SignInAPI;
import page.sign_in.entities.UserPass;

public class AddPage extends AppCompatActivity {
    private ActivityAddPageBinding binding;
    private String username;
    private MutableLiveData<ServerStringHolder> serverHolder;
    private ContactAPI contactAPI;
    private MutableLiveData<String> token;
    private MutableLiveData<String> status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // on click
        serverHolder = new MutableLiveData<>();
        token = new MutableLiveData<>();
        status = new MutableLiveData<>();
        binding.add.setOnClickListener(v -> {
            username = binding.username.getText().toString();
            if (!username.equals("")) {
                ServerStringRepository ssr = new ServerStringRepository(getApplicationContext());
                ssr.get(serverHolder);
            }
        });
        // get server address
        serverHolder.observe(this, serverObj -> {
            if (serverObj != null) {
                String url = serverObj.getServerAddress();
                contactAPI = new ContactAPI(null, url);
                SignInAPI signInAPI = new SignInAPI(url);
                Intent intent = getIntent();
                UserPass userPass = intent.getParcelableExtra("userPass");
                signInAPI.getToken(token, userPass, status);
            }
        });
        // get token
        token.observe(this, tokenGotten -> {
            if (tokenGotten != null) {
                contactAPI.addContact(status, tokenGotten, username);
            }
        });
        // set errors
        status.observe(this, string -> {
            if (string.equals("Friend successfully added :)")) {
                int textColor = ContextCompat.getColor(this, R.color.button);
                binding.setErr.setTextColor(textColor);
            } else if (string.equals("Wrong username or already added") || string.equals("No connection to the server")) {
                int textColor = ContextCompat.getColor(this, R.color.red);
                binding.setErr.setTextColor(textColor);
            } else if (string.equals("No internet connection")) {
                int textColor = ContextCompat.getColor(this, R.color.red);
                binding.setErr.setTextColor(textColor);
            }
            if (!string.equals(""))
                binding.setErr.setText(string);
        });
        binding.back.setOnClickListener(v -> {
            finish();
        });


        // add person
    }
}