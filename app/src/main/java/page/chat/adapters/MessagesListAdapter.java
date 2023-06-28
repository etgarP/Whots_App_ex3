package page.chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.entities.Message;

// converts the message list into something that can be displayed by the recycler view
public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {
    private final LayoutInflater mInflater;
    private List<Message> messages;
    private final String username;

    public MessagesListAdapter(Context context, String username) {
        this.mInflater = LayoutInflater.from(context);
        this.username = username;
    }
    private static final int VIEW_TYPE_LEFT = 0;
    private static final int VIEW_TYPE_RIGHT = 1;
    // picks the right position based on the user sending the message
    @Override
    public int getItemViewType(int position){
        Message message = messages.get(position);
        if(username.equals(message.getSender().getUsername())){
            return VIEW_TYPE_LEFT;
        }
        return VIEW_TYPE_RIGHT;
    }
    // inflates the layout
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType==VIEW_TYPE_LEFT) {
            itemView = this.mInflater.inflate(R.layout.left_message_layout, parent, false);
        }else{
            itemView = this.mInflater.inflate(R.layout.right_message_layout, parent, false);
        }
        return new MessageViewHolder(itemView);
    }
    // sets the message
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if(this.messages!=null){
            final Message current = this.messages.get(position);
            holder.tvContent.setText(current.getContent());
        }
    }
    // get message amount
    @Override
    public int getItemCount() {
        if(this.messages!=null){
            return this.messages.size();
        }
        return 0;
    }
    // sets messages
    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
    // holds the message view
    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;

        public MessageViewHolder(View itemView) {
            super(itemView);
            this.tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
