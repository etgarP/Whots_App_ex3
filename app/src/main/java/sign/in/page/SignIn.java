package sign.in.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.whotsapp.R;
import com.example.whotsapp.databinding.SignInBinding;

import sign.up.page.Register;


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
    }
}