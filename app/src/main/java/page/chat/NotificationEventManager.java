package page.chat;

import java.util.ArrayList;
import java.util.List;

import page.chat.viewmodels.MessagesViewModel;
// manages the listeners
public class NotificationEventManager {
    private static NotificationEventManager instance;
    private List<NotificationEventListener> listeners;
    // makes a new list
    private NotificationEventManager() {
        listeners = new ArrayList<>();
    }
    // singleton
    public static NotificationEventManager getInstance() {
        if (instance == null) {
            instance = new NotificationEventManager();
        }
        return instance;
    }
    // adds a listener
    public void registerListener(NotificationEventListener listener) {
        listeners.add(listener);
    }
    // removes a listener
    public void unregisterListener(NotificationEventListener listener) {
        listeners.remove(listener);
    }
    // notifies a listener
    public void dispatchNotificationEvent() {
        for (NotificationEventListener listener : listeners) {
            listener.onNotificationReceived();
        }
    }
}