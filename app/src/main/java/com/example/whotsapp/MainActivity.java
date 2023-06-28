package com.example.whotsapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.iid.FirebaseInstanceId;

import page.ServerStringHolder;
import page.WhichModeRep;
import page.chat.ContactPage;
import page.chat.ServerStringRepository;
import page.chat.entities.User;
import page.sign_in.SignIn;
import page.sign_in.SignInAPI;
import page.sign_in.UserSignedRepository;
import page.sign_in.entities.UserPass;
import page.sign_in.entities.UserSignedSaver;

public class MainActivity extends AppCompatActivity implements SignIn.SignInInteractionListener, ContactPage.RegisterInteractionListener {
    private SignInAPI signApi;
    private MutableLiveData<String> token;
    private MutableLiveData<User> user;
    private String username;
    private UserPass up;
    private MutableLiveData<String> ip;
    private MutableLiveData<ServerStringHolder> serverHolder;
    MutableLiveData<UserSignedSaver> userSaver;
    MutableLiveData<Integer> condition;
    MutableLiveData<String> result;
    private static boolean alreadyRecreated = false;

    // Implement the interface method to handle the fragment event
    @Override
    public void onFragmentEventSign(Bundle info) {
        // Replace the fragment with another fragment or perform any desired action
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ContactPage fragmentContacts = new ContactPage();
        fragmentContacts.setArguments(info);
        fragmentTransaction.replace(R.id.fragment_container, fragmentContacts);
        fragmentTransaction.commit();
    }
    @Override
    public void onFragmentEventReg(Bundle info) {
        // Replace the fragment with another fragment or perform any desired action
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SignIn fragmentSign = new SignIn();
        fragmentSign.setArguments(info);
        fragmentTransaction.replace(R.id.fragment_container, fragmentSign);
        fragmentTransaction.commit();
    }

    private void observeDarkMode() {
        result = new MutableLiveData<>();

        WhichModeRep whichModeRep = new WhichModeRep(getApplicationContext());
        whichModeRep.get(result);

        result.observe(this, string -> {
            if (string != null) {
                if (string.equals("light"))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                else if (string.equals("dark"))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    return;
                if(!alreadyRecreated){
                    recreate();
                    alreadyRecreated = true;
                }
            }
        });
    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        } else {
            // Permission already granted, proceed with notification
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        observeDarkMode();
        condition = new MutableLiveData<>(-1);
        MutableLiveData<String> firebaseToken = new MutableLiveData<>();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            firebaseToken.postValue(instanceIdResult.getToken());
            checkCondition(getApplicationContext());
        });
        condition.observeForever(num -> {
            if (num != null && num != -1) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (num == 1) {
                    ContactPage fragmentB = new ContactPage();
                    Bundle args = new Bundle();
                    String server = serverHolder.getValue().getServerAddress();
                    User user = userSaver.getValue().getSavedUser();
                    UserPass userPass = userSaver.getValue().getUserPass();
                    args.putString("url", server);
                    args.putParcelable("user", user);
                    args.putParcelable("userPass", userPass);
                    args.putString("firebaseToken", firebaseToken.getValue());
                    fragmentB.setArguments(args);

                    fragmentTransaction.replace(R.id.fragment_container, fragmentB);
                } else {
                    SignIn fragmentB = new SignIn();
                    Bundle args = new Bundle();
                    args.putString("firebaseToken", firebaseToken.getValue());
                    fragmentB.setArguments(args);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentB);
                }
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
    }
    private void checkCondition(Context context) {
        ServerStringRepository ssr = new ServerStringRepository(context);
        serverHolder = new MutableLiveData<>();
        ssr.get(serverHolder);

        userSaver = new MutableLiveData<>();
        UserSignedRepository uss = new UserSignedRepository(getApplicationContext());
        // getting the server address
        serverHolder.observeForever(serverObj -> {
            if (serverObj != null) {
                uss.get(userSaver);
            } else {
                condition.postValue(0);
            }
        });
        // getting the user
        userSaver.observeForever(gottenUser -> {
            if (gottenUser != null) {
                condition.postValue(1);
            } else {
                condition.postValue(0);
            }
        });
    }
}