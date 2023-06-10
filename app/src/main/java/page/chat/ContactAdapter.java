package page.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.entities.Contact;

public class ContactAdapter extends BaseAdapter {

    List<Contact> contacts;

    private static class ViewHolder{
        TextView displayName, when, lastMessage;
        ImageView profile;
        LinearLayout wholeContact;
    }

    public ContactAdapter(List<Contact> contacts) { this.contacts = contacts; }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() { return contacts.size(); }

    @Override
    public Object getItem(int position) { return contacts.get(position); }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.displayName = convertView.findViewById(R.id.contactDisplayName);
            viewHolder.when = convertView.findViewById(R.id.contactWhen);
            viewHolder.lastMessage = convertView.findViewById(R.id.contactLastMessage);
            viewHolder.profile = convertView.findViewById(R.id.contactPfp);
            viewHolder.wholeContact = convertView.findViewById(R.id.wholeContact);
            convertView.setTag(viewHolder);
        }

        Contact p = contacts.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.displayName.setText(p.getDisplayName());
        viewHolder.when.setText(p.getWhen());
        viewHolder.lastMessage.setText(p.getLastMessage());
        viewHolder.profile.setImageResource(p.getProfileImage());
        // adds transfer to another thing
//        viewHolder.wholeContact.setOnClickListener(v -> {
//
//        });

        return convertView;
    }
}
