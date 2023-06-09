package sign.up.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.ActivityRegisterBinding;
import com.example.whotsapp.databinding.SignInBinding;

import sign.in.page.SignIn;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toSign.setOnClickListener(view -> {
            finish();
        });
    }
}