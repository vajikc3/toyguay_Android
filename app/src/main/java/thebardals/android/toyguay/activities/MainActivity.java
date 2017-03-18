package thebardals.android.toyguay.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.ToyGuayApp;
import thebardals.domain.Toy;
import thebardals.domain.util.RealmDatabaseFileUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);
        Realm realm = ToyGuayApp.getRealm();

//        realm.beginTransaction();
//        realm.deleteAll();
//        realm.commitTransaction();
//
//        Category c1 =  new Category();
//        c1.setId(1);
//        c1.setName("Construcciones");
//        Category c2 = new Category();
//        c2.setId(2);
//        c2.setName("Muñecas");



        // get id
        RealmResults<Toy> results = realm.where(Toy.class).findAllSorted("id");
        long lastId = 0;
        if (results != null && results.size() > 0) {
            lastId = results.last().getId();
        }

        // save Toys
        realm.beginTransaction();

        for (long i = lastId + 1; i < lastId + 2; i++) {

//        Category c1 =  new Category();
//        c1.setId(1);
//        c1.setName("Construcciones");
//        Category c2 = new Category();
//        c2.setId(2);
//        c2.setName("Muñecas");


            //save Toy
            Toy toy = new Toy();
            toy.setId(i);
            toy.setName("LEGOSSSS " + i + UUID.randomUUID());
            toy.setDescription("Proof creating toys... " + UUID.randomUUID());
            toy.setState("toSell");
            toy.setPrice(9.99);
            toy.setCreatedAt(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));

//            toy.setCategories(new RealmList<Category>(c1, c2));

            realm.copyToRealm(toy);
        }
        realm.commitTransaction();

        RealmResults<Toy> allToys = realm.where(Toy.class).findAllSorted("id");
        for (Toy t: allToys) {
            Log.w("TOYS", t.getName() + " - " + t.getId());
        }

        File file = new RealmDatabaseFileUtil().exportRealmDatabaseFile(this, realm, "toy.realm");
        Log.d("File", "./adb pull /sdcard/toy.realm");

    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
