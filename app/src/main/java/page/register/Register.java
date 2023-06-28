package page.register;

import static page.register.ValidationUtils.validateConfirmPass;
import static page.register.ValidationUtils.validateDisplayName;
import static page.register.ValidationUtils.validatePassword;
import static page.register.ValidationUtils.validatePicture;
import static page.register.ValidationUtils.validateUsername;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.ActivityRegisterBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import page.sign_in.entities.UserPassName;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private EditText usernameEt, passwordEt, cPasswordEt, displayNameEt;
    private LinearLayout pictureBtn;
    Uri imageUri;
    MutableLiveData<String> usernameM, passwordM, pictureM, displayNameM, cPasswordM;
    MutableLiveData<String> goodPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setting things up
        usernameEt = binding.username;
        passwordEt = binding.password;
        cPasswordEt = binding.rePassword;
        displayNameEt = binding.displayName;
        pictureBtn = binding.uploadImageButton;
        usernameM = new MutableLiveData<String>();
        passwordM = new MutableLiveData<String>();
        pictureM = new MutableLiveData<String>();
        displayNameM = new MutableLiveData<String>();
        cPasswordM = new MutableLiveData<String>();
        goodPost = new MutableLiveData<>();
        imageUri = null;
        binding.toSign.setOnClickListener(view -> {
            finish();
        });
        // registering
        binding.register.setOnClickListener(view -> {
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            String cPassword = cPasswordEt.getText().toString();
            String displayName = displayNameEt.getText().toString();
            // checking everything is valid
            boolean checked = checkValidData(username, password, cPassword, displayName);
            // registering in the server
            if (checked) {
                String picture64 = getBase64(imageUri);
                if (picture64 == null) return;
                RegisterApi ra = new RegisterApi(getIntent().getStringExtra("url"));
                ra.createUser(new UserPassName(username, password, displayName, picture64), goodPost);
            }
        });
        // when setting image
        pictureBtn.setOnClickListener(v -> {
            // Create an intent to pick an image from the gallery or take a photo
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            // Start the activity to select an image and handle the result using the launcher
            launcher.launch(intent);
            System.out.println(imageUri);
        });
        // listener for going back
        binding.back.setOnClickListener(v -> {
            finish();
        });
        // putting error messages if needed, or removing them
        passwordM.observe(this, password -> {
            if (password.equals(""))
                binding.passErr.setText(null);
            else
                binding.passErr.setText(passwordM.getValue());
        });
        pictureM.observe(this, picture -> {
            if (picture.equals(""))
                binding.picErr.setText(null);
            else
                binding.picErr.setText(picture);
        });
        displayNameM.observe(this, displayName -> {
            if (displayName.equals(""))
                binding.displayNameErr.setText(null);
            else
                binding.displayNameErr.setText(displayNameM.getValue());
        });
        cPasswordM.observe(this, cPassword -> {
            if (cPassword.equals(""))
                binding.cPassErr.setText(null);
            else
                binding.cPassErr.setText(cPasswordM.getValue());
        });
        usernameM.observe(this, username -> {
            if (username.equals(""))
                binding.UserErr.setText(null);
            else
                binding.UserErr.setText(usernameM.getValue());
        });
        goodPost.observeForever(string -> {
            if (string != null) {
                if (string.equals("done")) {
                    finish();
                }
                else if (string.equals("Image too big")) {
                    binding.picErr.setText("Image too big");
                } else if (string.equals("No connection")) {
                    binding.err.setText("No connection");
                } else if (string.equals("User already exists")) {
                    binding.err.setText("User already exists");
                }
            }
        });
    }
    // lunches the picture intent
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    // Get the image URI from the result
                    imageUri = result.getData().getData();
                }
            });
    // validating the data
    private boolean checkValidData(String username, String password, String cPassword, String displayName) {
        boolean a = validateUsername(username, usernameM);
        boolean b = validatePassword(password, passwordM);
        boolean c = validateConfirmPass(password, cPassword, cPasswordM);
        boolean d = validateDisplayName(displayName, displayNameM);
        boolean e = validatePicture(imageUri, pictureM);
        return  a && b && c && d && e;
    }
    // transforming uril image to base64 that works with the server
    String getBase64(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String base64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
            String base64Data = base64String.replaceAll("\\s", ""); // Remove whitespace
            base64Data = base64Data.replaceAll("\\n", ""); // Remove line breaks

            return "data:image/png;base64," + base64Data;
        } catch (IOException e) {
            return null;
        }
    }
}