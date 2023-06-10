package page.chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.entities.Contact;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView displayName, when, lastMessage;
        private final ImageView profile;

        private ContactViewHolder (View itemView) {
            super(itemView);
            displayName = itemView.findViewById(R.id.contactDisplayName);
            when = itemView.findViewById(R.id.contactWhen);
            lastMessage = itemView.findViewById(R.id.contactLastMessage);
            profile = itemView.findViewById(R.id.contactPfp);
        }
    }
    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    public ContactsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
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
            holder.lastMessage.setText(current.getLastMessage());
            holder.profile.setImageResource(current.getProfileImage());
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
