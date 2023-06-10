package page.chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whotsapp.R;

import java.util.List;

import page.chat.entities.Message;
import page.chat.viewmodels.MessageViewModel;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{
    class MessageViewHolder extends RecyclerView.ViewHolder{
        private final TextView content, sender;

        private MessageViewHolder(View itemView){
            super(itemView);
            this.content = itemView.findViewById(R.id.content);
            this.sender = itemView.findViewById(R.id.sender);
        }
    }

    private  final LayoutInflater mInflater;
    private List<Message> messages;
    public MessagesListAdapter(Context context) {
        this.mInflater=LayoutInflater.from(context);
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.,message_layout, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position){
        if(this.messages != null){
            final Message current = this.messages.get(position);
            holder.content.setContent(current.getContent());
            holder.sender.setSender(current.getSender());
        }
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }

    public int getItemCount(){
        if(this.messages!=null){
            return messages.size();
        }
        return 0;
    }
}
