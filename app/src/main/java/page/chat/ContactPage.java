package page.chat;


import android.content.Context;
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

import page.chat.adapters.ContactsListAdapter;
import page.chat.entities.Contact;
import page.chat.entities.User;
import page.chat.repositories.ContactsRepository;
import page.chat.viewmodels.ContactsViewModel;
import page.sign_in.UserSignedRepository;

public class ContactPage extends Fragment {

    List<Contact> contacts;
    private ContactsViewModel viewModel;
    private ActivityContactPageBinding binding;
    private String url;
    private String token;
    private RegisterInteractionListener interactionListener;
    public interface RegisterInteractionListener {
        void onFragmentEventReg();
    }
    public void setFragmentInteractionListener(RegisterInteractionListener listener) {
        this.interactionListener = listener;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interactionListener = (RegisterInteractionListener) context;
        setFragmentInteractionListener(interactionListener);
    }
    // Call this method when the event occurs in the fragment
    private void triggerEvent() {
        if (interactionListener != null) {
            interactionListener.onFragmentEventReg();
        }
    }
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

        viewModel = new ContactsViewModel(requireContext().getApplicationContext(), url, token);
        RecyclerView lstPosts = binding.lstPosts;
        final ContactsListAdapter adapter = new ContactsListAdapter(requireContext());
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(requireContext()));
        SwipeRefreshLayout refreshLayout = binding.refreshLayout;
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reload();
            binding.refreshLayout.setRefreshing(false);
        });
        viewModel.get().observe(getViewLifecycleOwner(), adapter::setContacts);

        MutableLiveData<Integer> observeDelete = new MutableLiveData<>();
        binding.logOut.setOnClickListener(v -> {
            new Thread(() -> {
                UserSignedRepository usr = new UserSignedRepository(requireContext().getApplicationContext());
                ContactsRepository cr = new ContactsRepository(requireContext().getApplicationContext(), url, token);
                cr.deleteDataMain();
                usr.deleteDataMain();
                usr.get(new MutableLiveData<>());
                observeDelete.postValue(1);
            }).start();
        });
        observeDelete.observe(getViewLifecycleOwner(), num -> {
            if (num == 1){
                triggerEvent();
            }
        });
    }

    private void setDetails() {
        if (getArguments() != null) {
            User user = getArguments().getParcelable("user");
            url = getArguments().getString("url");
            token = getArguments().getString("token");
            binding.displayNamePlace.setText(user.getDisplayName());
            binding.pfp.setImageBitmap(user.getProfilePicBit());
        }
    }
}