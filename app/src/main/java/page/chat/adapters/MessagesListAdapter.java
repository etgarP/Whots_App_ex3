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

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {

    class MessageViewHolder extends  RecyclerView.ViewHolder{
        private final TextView tvContent;
//        private final TextView tvSender;

        public MessageViewHolder(View itemView) {
            super(itemView);
            this.tvContent = itemView.findViewById(R.id.tvContent);
//            this.tvSender = itemView.findViewById(R.id.tvSender);
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> messages;

    public MessagesListAdapter(Context context) { this.mInflater=LayoutInflater.from(context); }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(R.id.tvSender == "")//todo if left/right
        View itemView = this.mInflater.inflate(R.layout.left_message_layout, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if(this.messages!=null){
            final Message current = this.messages.get(position);
            holder.tvContent.setText(current.getContent());
//            holder.tvSender.setText(current.getSender().getUsername());
        }
    }

    @Override
    public int getItemCount() {
        if(this.messages!=null){
            return this.messages.size();
        }
        return 0;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    private List<Message> getMessages(){ return this.messages;}
}
