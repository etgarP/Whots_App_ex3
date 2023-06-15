package page.sign_in;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.SignInBinding;

import page.ServerStringHolder;
import page.Settings;
import page.chat.ContactPage;
import page.chat.ServerStringRepository;
import page.chat.entities.User;
import page.register.Register;
import page.sign_in.entities.UserPass;
import page.sign_in.entities.UserSignedSaver;


public class SignIn extends AppCompatActivity {
    private SignInBinding binding;
    private SignInAPI signApi;
    private MutableLiveData<String> token;
    private MutableLiveData<User> user;
    private String username;
    private UserPass up;
    private MutableLiveData<String> ip;
    private MutableLiveData<ServerStringHolder> serverHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ServerStringRepository ssr = new ServerStringRepository(getApplicationContext());
        serverHolder = new MutableLiveData<>();
        ssr.get(serverHolder);
        binding.toRegister.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, Register.class);
            startActivity(intent);
        });
        binding.settings.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, Settings.class);
            startActivity(intent);
        });
        // seeing if im logged in
        MutableLiveData<UserSignedSaver> userSaver = new MutableLiveData<>();
        token = new MutableLiveData<>();
        user = new MutableLiveData<>();
        UserSignedRepository uss = new UserSignedRepository(getApplicationContext());
        serverHolder.observe(this, serverObj -> {
            if (serverObj != null) {
                signApi = new SignInAPI(serverObj.getServerAddress());
                uss.get(userSaver);
            }
        });
        userSaver.observe(this, gottenUser -> {
            if (gottenUser != null) {
                signApi.getToken(token, gottenUser.getUserPass());
            }
        });
        // after getting the token going in to contacts, if im logged
        token.observe(this, tokenString -> {
            if (tokenString != null) {
                if (userSaver.getValue() != null) {
                    Intent intent = new Intent(SignIn.this, ContactPage.class);
                    intent.putExtra("token", tokenString);
                    intent.putExtra("user", userSaver.getValue().getSavedUser());
                    startActivity(intent);
                } else {
                    signApi.getUserPassName(tokenString, username, user);
                }
            }
        });
        // if the user isn't connected
        binding.LoggingIn.setOnClickListener(view -> {
            String username1 = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            if (username1.equals("") && password.equals("")) {
                up = new UserPass("FFFFf4FFFFf4", "FFFFf4FFFFf4");
                username = "FFFFf4FFFFf4";
            } else {
                up = new UserPass(username1, password);
                username = username1;
            }
            if (token.getValue() == null)
                signApi.getToken(token, up);
            else signApi.getUserPassName(token.getValue(), username, user);
        });
        user.observe(this, gottenUser -> {
            UserSignedSaver userSignedSaver = new UserSignedSaver(1, gottenUser, up);
            uss.insert(up, gottenUser);
            Intent intent = new Intent(SignIn.this, ContactPage.class);
            intent.putExtra("token", token.getValue());
            intent.putExtra("user", gottenUser);
            startActivity(intent);
        });
    }
}