package page;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.ActivitySettingsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import page.chat.ServerStringRepository;

public class Settings extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    private boolean checkIp(String ip) {
        String IP_ADDRESS_REGEX =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return Pattern.matches(IP_ADDRESS_REGEX, ip);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<MutableLiveData<Integer>> whichCall = new ArrayList<>();
        whichCall.add(new MutableLiveData<>(0));
        whichCall.add(new MutableLiveData<>(1));
        List<MutableLiveData<String>> serverAddress = new ArrayList<>();
        serverAddress.add(new MutableLiveData<>());
        serverAddress.add(new MutableLiveData<>());
        ServerStringRepository ssh = new ServerStringRepository(getApplicationContext());
        ssh.get(serverAddress.get(0), whichCall.get(0));
        binding.setAddress.setOnClickListener(view -> {
            ssh.get(serverAddress.get(1), whichCall.get(1));
        });
        whichCall.get(0).observe(this, num -> {
            if (num == 2) {
                String originalString = serverAddress.get(0).getValue();
                String startToRemove = "http://";
                String endToRemove = "12345:/api/";
                String modifiedString = originalString.substring(startToRemove.length(), originalString.length() - endToRemove.length());
                binding.serverText.setText(modifiedString);
            }
        });
        binding.back.setOnClickListener(view -> {
            finish();
        });
        whichCall.get(1).observe(this, num -> {
            if (num == 2) {
                if (checkIp(String.valueOf(binding.serverText.getText()))) {
                    String ip = "http://" + String.valueOf(binding.serverText.getText()) + ":12345/api/";
                    if (serverAddress.get(1).getValue() == null) {
                        Boolean b = String.valueOf(binding.serverText.getText()).equals("");
                        if (b) {
                        } else {
                            ssh.insert(ip);
                        }
                    } else {
                        ssh.set(ip);
                    }
                    binding.ipErr.setText("");
                } else {
                    binding.ipErr.setText("Invalid IP Address");
                }
            }
        });
    }
}