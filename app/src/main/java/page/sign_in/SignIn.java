package page.sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.whotsapp.databinding.SignInBinding;

import page.chat.ContactPage;
import page.register.Register;


public class SignIn extends AppCompatActivity {
    private SignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toRegister.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, Register.class);
            startActivity(intent);
        });
        binding.LoggingIn.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, ContactPage.class);
            startActivity(intent);
        });
    }
}