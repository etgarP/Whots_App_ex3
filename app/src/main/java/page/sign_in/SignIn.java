package page.sign_in;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whotsapp.databinding.SignInBinding;

import page.register.Register;
import page.settings;


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
            Intent intent = new Intent(SignIn.this, settings.class);
            startActivity(intent);
        });
    }
}