package page.chat;

import page.chat.adapters.MessagesListAdapter;
import page.chat.viewmodels.MessagesViewModel;

public interface NotificationEventListener {
    void onNotificationReceived();
}