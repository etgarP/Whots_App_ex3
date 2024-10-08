package page.chat;


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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whotsapp.databinding.ActivityContactPageBinding;

import java.util.List;

import page.Settings;
import page.chat.adapters.ContactsListAdapter;
import page.chat.entities.Contact;
import page.chat.entities.User;
import page.chat.repositories.ContactsRepository;
import page.chat.viewmodels.ContactsViewModel;
import page.sign_in.SignInAPI;
import page.sign_in.UserSignedRepository;
import page.sign_in.entities.UserPass;
// a page for seeing the contacts
public class ContactPage extends Fragment implements NotificationEventListener {

    List<Contact> contacts;
    private ContactsViewModel viewModel;
    private ActivityContactPageBinding binding;
    private String url;
    private UserPass userPass;
    private String firebaseToken;
    private RegisterInteractionListener interactionListener;

    // reloads contacts when we get a notification
    @Override
    public void onNotificationReceived() {
        if (viewModel != null) {
            viewModel.reload();
        }
    }
    // sets interface for main activity
    public interface RegisterInteractionListener {
        void onFragmentEventReg(Bundle info);
    }
    // sets main activity as a listener
    public void setFragmentInteractionListener(RegisterInteractionListener listener) {
        this.interactionListener = listener;
    }
    // sets main activity as the interaction listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interactionListener = (RegisterInteractionListener) context;
        setFragmentInteractionListener(interactionListener);
    }
    // trigger the event of switching to sign in
    private void triggerEvent() {
        if (interactionListener != null) {
            Bundle info = new Bundle();
            info.putString("firebaseToken", firebaseToken);
            interactionListener.onFragmentEventReg(info);
        }
    }
    // sets binding
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityContactPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDetails();
        viewModel = new ContactsViewModel(requireContext().getApplicationContext(), url);
        // register this for notifications
        NotificationEventManager.getInstance().registerListener(this);
        // sets adapter
        RecyclerView lstPosts = binding.lstPosts;
        final ContactsListAdapter adapter = new ContactsListAdapter(requireContext(), url, userPass);
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(requireContext()));
        SwipeRefreshLayout refreshLayout = binding.refreshLayout;
        // reloads on refresh
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
            binding.refreshLayout.setRefreshing(false);
        });
        // gets token
        viewModel.get().observe(getViewLifecycleOwner(), adapter::setContacts);
        MutableLiveData<String> token = new MutableLiveData<>();
        SignInAPI api = new SignInAPI(url);
        api.getToken(token, userPass, null);
        token.observeForever(tokenString -> {
            if (tokenString != null) {
                viewModel.setTokens(tokenString, firebaseToken);
            }
        });
        // exists into sign
        MutableLiveData<Integer> observeDelete = new MutableLiveData<>();
        binding.exit.setOnClickListener(v -> new Thread(() -> {
            UserSignedRepository usr = new UserSignedRepository(requireContext().getApplicationContext());
            ContactsRepository cr = new ContactsRepository(requireContext().getApplicationContext(), url);
            cr.deleteDataMain();
            usr.deleteDataMain();
            usr.get(new MutableLiveData<>());
            observeDelete.postValue(1);
        }).start());
        observeDelete.observe(getViewLifecycleOwner(), num -> {
            if (num == 1){
                triggerEvent();
            }
        });
        // goes to settings
        binding.settingsContacts.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Settings.class);
            startActivity(intent);
        });
        // goes to add page
        binding.add.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddPage.class);
            intent.putExtra("userPass", userPass);
            startActivity(intent);
        });
    }
    // gets the arguments
    private void setDetails() {
        if (getArguments() != null) {
            User user = getArguments().getParcelable("user");
            userPass = getArguments().getParcelable("userPass");
            url = getArguments().getString("url");
            firebaseToken = getArguments().getString("firebaseToken");
            binding.displayNamePlace.setText(user.getDisplayName());
            binding.pfp.setImageBitmap(user.getProfilePicBit());

        }
    }
    // reloads on resume
    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null) {
            viewModel.reload();
        }
    }
}