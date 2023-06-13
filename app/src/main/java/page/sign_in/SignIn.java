package page.sign_in;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.SignInBinding;

import page.chat.ContactPage;
import page.chat.entities.User;
import page.register.Register;
import page.sign_in.entities.UserPass;


public class SignIn extends AppCompatActivity {
    private SignInBinding binding;
    private SignInAPI signApi;
    private MutableLiveData<String> token;
    private MutableLiveData<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toRegister.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, Register.class);
            startActivity(intent);
        });
        token = new MutableLiveData<>();
        user = new MutableLiveData<>();
        binding.LoggingIn.setOnClickListener(view -> {
            signApi = new SignInAPI();
            signApi.getToken(token, new UserPass("FFFFf4FFFFf4", "FFFFf4FFFFf4"));

        });
        token.observe(this, userToken -> {
            String tokenString = token.getValue();
            signApi.getUserPassName(tokenString, "FFFFf4FFFFf4", user);
        });
        user.observe(this, gottenUser -> {
            Intent intent = new Intent(SignIn.this, ContactPage.class);
            intent.putExtra("token", token.getValue());
            intent.putExtra("user", gottenUser);
            startActivity(intent);
        });
    }
}