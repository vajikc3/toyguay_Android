package thebardals.android.toyguay;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by jacobo on 5/2/17.
 */

public class ToyGuayApp extends Application {
    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // init your app

        ToyGuayApp.appContext = new WeakReference<Context>(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext() {
        if (appContext != null) {
            return ToyGuayApp.appContext.get();
        }
        return null;
    }
}
