package page;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.ActivitySettingsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import page.chat.ServerStringRepository;

public class Settings extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    private Dialog dialog;

    private void setDarkMode() {
        MutableLiveData<String> result = new MutableLiveData<>();
        WhichModeRep whichModeRep = new WhichModeRep(getApplicationContext());
        whichModeRep.get(result);
        result.observe(this, string -> {
            if (string != null) {
                if (string.equals("light"))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                else if (string.equals("dark"))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else if (string.equals("auto")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else { return; }
                recreate();
            }
        } );
    }

    private void setPopup() {
        // Create an instance of AlertDialog.Builder
        binding.darkMode.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            WhichModeRep whichModeRep = new WhichModeRep(getApplicationContext());
            // Set the title and options
            builder.setTitle("Choose an Option")
                .setItems(new CharSequence[]{"Light mode", "Dark mode", "Device Default"}, (dialogInterface, which) -> {
                    // Handle the selected option
                    switch (which) {
                        case 0:
                            whichModeRep.update("light");
                            break;
                        case 1:
                            whichModeRep.update("dark");
                            break;
                        case 2:
                            whichModeRep.update("auto");
                            break;
                    }
                });
            // Create and show the AlertDialog
            dialog = builder.create();
            dialog.setOnDismissListener(dialogInterface -> {
                setDarkMode();
            });
            dialog.show();
        });
    }


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
        setPopup();
        binding.setAddress.setOnClickListener(view -> {
            ssh.get(serverAddress.get(1), whichCall.get(1));
        });
        whichCall.get(0).observe(this, num -> {
            if (serverAddress.get(0).getValue() != null) {
                if (num == 2) {
                    String originalString = serverAddress.get(0).getValue();
                    String startToRemove = "http://";
                    String endToRemove = "12345:/api/";
                    String modifiedString = originalString.substring(startToRemove.length(), originalString.length() - endToRemove.length());
                    binding.serverText.setText(modifiedString);
                }
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
    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}