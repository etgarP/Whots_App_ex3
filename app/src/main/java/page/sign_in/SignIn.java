package page.sign_in;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.example.whotsapp.databinding.SignInBinding;

import page.ServerStringHolder;
import page.Settings;
import page.chat.ContactPage;
import page.chat.ServerStringRepository;
import page.chat.entities.User;
import page.register.Register;
import page.sign_in.entities.UserPass;


public class SignIn extends Fragment {
    private SignInBinding binding;
    private SignInAPI signApi;
    private MutableLiveData<String> token;
    private MutableLiveData<User> user;
    private String username;
    private UserPass up;
    private String firebaseToken;
    private MutableLiveData<String> ip;
    private MutableLiveData<ServerStringHolder> serverHolder;
    private SignInInteractionListener interactionListener;
    private MutableLiveData<String> err;

    public interface SignInInteractionListener {
        void onFragmentEventSign(Bundle info);
    }
    public void setFragmentInteractionListener(SignInInteractionListener listener) {
        this.interactionListener = listener;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interactionListener = (SignInInteractionListener) context;
        setFragmentInteractionListener(interactionListener);
    }
    // Call this method when the event occurs in the fragment
    private void triggerEvent(Bundle info) {
        if (interactionListener != null) {
            interactionListener.onFragmentEventSign(info);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ServerStringRepository ssr = new ServerStringRepository(requireContext());
        serverHolder = new MutableLiveData<>();

        binding.toRegister.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Register.class);
            startActivity(intent);
        });

        binding.settings.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Settings.class);
            startActivity(intent);
        });

        token = new MutableLiveData<>();
        user = new MutableLiveData<>();
        UserSignedRepository uss = new UserSignedRepository(requireContext());

        binding.LoggingIn.setOnClickListener(v -> {
            String username1 = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            if (username1.equals("") && password.equals("")) {
                up = new UserPass("FFFFf4FFFFf4", "FFFFf4FFFFf4");
                username = "FFFFf4FFFFf4";
            } else {
                up = new UserPass(username1, password);
                username = username1;
            }
            ssr.get(serverHolder);
        });
        err = new MutableLiveData<>();

        serverHolder.observeForever(serverObj -> {
            if (serverObj != null) {
                signApi = new SignInAPI(serverObj.getServerAddress());
                signApi.getToken(token, up, err);
            } else {
                binding.setAddress.setText("Please set a valid server address.");
            }
        });

        token.observe(getViewLifecycleOwner(), tokenString -> {
            if (tokenString != null) {
                if (getArguments() != null) {
                    firebaseToken = getArguments().getString("firebaseToken");
                    signApi.getUserPassName(firebaseToken, tokenString, username, user, err);
                }
            }
        });

        user.observe(getViewLifecycleOwner(), gottenUser -> {
            uss.insert(up, gottenUser);
            Bundle args = new Bundle();
            args.putParcelable("user", gottenUser);
            args.putParcelable("userPass", up);
            args.putString("firebaseToken", firebaseToken);
            if (serverHolder.getValue() != null) {
                args.putString("url", serverHolder.getValue().getServerAddress());
            } else {
                args.putString("url", "http://10.0.0.2:12345/api/");
            }
            triggerEvent(args);
        });
        err.observeForever(string -> {
            if (!string.equals("")) {
                binding.setErr.setText(string);
            }
        });
    }
}
