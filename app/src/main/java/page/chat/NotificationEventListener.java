package page.chat;

import page.chat.adapters.MessagesListAdapter;
import page.chat.viewmodels.MessagesViewModel;
// listens for notifications
public interface NotificationEventListener {
    void onNotificationReceived();
}