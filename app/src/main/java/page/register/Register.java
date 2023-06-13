package page.register;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whotsapp.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private EditText usernameEt, passwordEt, cPasswordEt, displayNameEt;
    private Button pictureBtn;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        usernameEt = binding.username;
        passwordEt = binding.password;
        cPasswordEt = binding.rePassword;
        displayNameEt = binding.displayName;
        pictureBtn = binding.uploadImageButton;
        imageUri = null;
        binding.toSign.setOnClickListener(view -> {
            finish();
        });
        binding.register.setOnClickListener(view -> {
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            String cPassword = cPasswordEt.getText().toString();
            String displayName = displayNameEt.getText().toString();
        });
        pictureBtn.setOnClickListener(v -> {
            // Create an intent to pick an image from the gallery or take a photo
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");

            // Start the activity to select an image and handle the result using the launcher
            launcher.launch(intent);
            System.out.println(imageUri);
        });
        binding.back.setOnClickListener(v -> {
            finish();
        });
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    // Get the image URI from the result
                    imageUri = result.getData().getData();
                }
            });

}