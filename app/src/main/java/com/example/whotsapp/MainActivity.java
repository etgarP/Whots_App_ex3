package com.example.whotsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import page.ServerStringHolder;
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
    public void onFragmentEventReg() {
        // Replace the fragment with another fragment or perform any desired action
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SignIn fragmentSign = new SignIn();
        fragmentTransaction.replace(R.id.fragment_container, fragmentSign);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        condition = new MutableLiveData<>(-1);
        checkCondition(getApplicationContext()); // Replace with your own condition logic
        condition.observeForever(num -> {
            if (num != null && num != -1) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (num == 1) {
                    ContactPage fragmentB = new ContactPage();
                    Bundle args = new Bundle();
                    String server = serverHolder.getValue().getServerAddress();
                    String token1 = token.getValue();
                    User user = userSaver.getValue().getSavedUser();
                    args.putString("url", serverHolder.getValue().getServerAddress());
                    args.putString("token", token.getValue());
                    args.putParcelable("user", userSaver.getValue().getSavedUser());
                    fragmentB.setArguments(args);

                    fragmentTransaction.replace(R.id.fragment_container, fragmentB);
                    fragmentTransaction.addToBackStack(null);
                } else {
                    SignIn fragmentB = new SignIn();
                    fragmentTransaction.replace(R.id.fragment_container, fragmentB);
                }
                fragmentTransaction.commit();
            }
        });
    }

    // Replace with your own condition logic
    private void checkCondition(Context context) {
        ServerStringRepository ssr = new ServerStringRepository(context);
        serverHolder = new MutableLiveData<>();
        ssr.get(serverHolder);

        userSaver = new MutableLiveData<>();
        token = new MutableLiveData<>();
        UserSignedRepository uss = new UserSignedRepository(getApplicationContext());

        serverHolder.observeForever(serverObj -> {
            if (serverObj != null) {
                signApi = new SignInAPI(serverObj.getServerAddress());
                uss.get(userSaver);
            } else {
                condition.postValue(0);
            }
        });

        userSaver.observeForever(gottenUser -> {
            if (gottenUser != null) {
                signApi.getToken(token, gottenUser.getUserPass());
            } else {
                condition.postValue(0);
            }
        });

        token.observeForever(tokenString -> {
            if (tokenString != null) {
                if (userSaver.getValue() != null) {
                    condition.postValue(1);
                } else {
                    condition.postValue(0);
                }
            }
            else {
                condition.postValue(0);
            }
        });
    }
}