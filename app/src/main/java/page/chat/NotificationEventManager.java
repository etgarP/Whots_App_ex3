package page.chat;

import java.util.ArrayList;
import java.util.List;

import page.chat.viewmodels.MessagesViewModel;

public class NotificationEventManager {
    private static NotificationEventManager instance;
    private List<NotificationEventListener> listeners;

    private NotificationEventManager() {
        listeners = new ArrayList<>();
    }

    public static NotificationEventManager getInstance() {
        if (instance == null) {
            instance = new NotificationEventManager();
        }
        return instance;
    }

    public void registerListener(NotificationEventListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(NotificationEventListener listener) {
        listeners.remove(listener);
    }

    public void dispatchNotificationEvent() {
        for (NotificationEventListener listener : listeners) {
            listener.onNotificationReceived();
        }
    }
}