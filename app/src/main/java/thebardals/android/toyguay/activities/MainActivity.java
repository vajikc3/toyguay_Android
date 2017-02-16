package thebardals.android.toyguay.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.ToyGuayApp;
import thebardals.domain.Toy;
import thebardals.domain.util.RealmDatabaseFileUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        verifyStoragePermissions(this);

        Toy toy = new Toy();
        toy.setId(1);
        toy.setName("Muñeca Diabólica");
        toy.setDescription("Proof 4 creating toys...");
        toy.setState("toSell");
        toy.setPrice(9.99);

        Realm realm = Realm.getInstance(ToyGuayApp.getRealm().getConfiguration());
        realm.beginTransaction();
        realm.copyToRealm(toy);
        realm.commitTransaction();

        new RealmDatabaseFileUtil().exportRealmDatabaseFile(this, realm, "toy.realm");

    }

//    // Storage Permissions
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//
//    /**
//     * Checks if the app has permission to write to device storage
//     *
//     * If the app does not has permission then the user will be prompted to grant permissions
//     *
//     * @param activity
//     */
//    public static void verifyStoragePermissions(Activity activity) {
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }
}
