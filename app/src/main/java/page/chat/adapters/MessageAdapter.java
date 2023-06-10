package page.chat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.entities.Message;

public class MessageAdapter extends BaseAdapter {
    List<Message> messages;

    private static class ViewHolder{
        TextView content, sender;
        LinearLayout wholeMessage;
    }

    public MessageAdapter(List<Message> messages){
        this.messages = messages;
    }

    @Override
    public long getItemId (int position) {
        return 0;
    }

    @Override
    public int getCount(){
        return messages.size();
    }

    @Override
    public Object getItem(int position){
        return messages.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.content = convertView.findViewById(R.id.messageContent);
            viewHolder.sender = convertView.findViewById(R.id.messageSender);
            viewHolder.wholeMessage = convertView.findViewById(R.id.wholeMessage);
            convertView.setTag(viewHolder);
        }

        Message message = messages.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.content.setText(message.getContent());
        viewHolder.sender.setText(message.getSender());

        return convertView;
    }
}
