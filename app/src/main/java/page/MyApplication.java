package page;

import android.app.Application;
import android.content.Context;

// this is to get the context
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

}