package thebardals.android.toyguay;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jacobo on 5/2/17.
 */

public class ToyGuayApp extends Application {
    private static WeakReference<Context> appContext;
    private static Realm realm;

    public static Realm getRealm() {
        return realm;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ToyGuayApp.appContext = new WeakReference<Context>(getApplicationContext());

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
        .name("toyguay.realm")
        .build();
        this.realm = Realm.getInstance(config);

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
