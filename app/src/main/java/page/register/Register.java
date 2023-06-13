package page.register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whotsapp.databinding.ActivityRegisterBinding;

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