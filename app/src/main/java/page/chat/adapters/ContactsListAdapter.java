package page.chat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.MessagesPage;
import page.chat.entities.Contact;
import page.sign_in.entities.UserPass;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    private final String url;
    private UserPass userPass;

    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    private Context context;
    public ContactsListAdapter(Context context, String url, UserPass userPass) {
        this.url = url;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.userPass=userPass;
    }
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView displayName, when, lastMessage;
        private final ImageView profile;
        private LinearLayout linearLayout;
        private Contact contact;

        private ContactViewHolder (View itemView) {
            super(itemView);
            displayName = itemView.findViewById(R.id.contactDisplayName);
            when = itemView.findViewById(R.id.contactWhen);
            lastMessage = itemView.findViewById(R.id.contactLastMessage);
            profile = itemView.findViewById(R.id.contactPfp);
            linearLayout = itemView.findViewById(R.id.wholeContact);
        }
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position)  {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.displayName.setText(current.getDisplayName());
            holder.when.setText(current.getWhen());
            holder.lastMessage.setText(current.getLastMessageText());
            holder.profile.setImageBitmap(current.getProfileImage());
            holder.contact = current;
            holder.linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, MessagesPage.class);
                intent.putExtra("User", holder.contact.getUser());
                intent.putExtra("id", holder.contact.getId());
                intent.putExtra("url", url);
                intent.putExtra("userPass", userPass);
                context.startActivity(intent);
            });
        }
    }
    public void setContacts(List<Contact> s) {
        contacts = s;
        notifyDataSetChanged();
    }
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        }
        else return 0;
    }
}