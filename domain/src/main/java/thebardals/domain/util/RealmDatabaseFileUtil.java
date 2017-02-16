package thebardals.domain.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.realm.Realm;
import io.realm.internal.IOException;

public class RealmDatabaseFileUtil {
    public File exportRealmDatabaseFile(Context context, Realm realm, String realmDBFileName) {
        if (realm == null || context == null || realmDBFileName == null || realmDBFileName.length()==0) {
            throw new IllegalArgumentException("exportRealmDatabaseFile Null input data");
        }

        if (!realm.isClosed()) {
            realm.close();
        }

        File exportRealmFile = new File(realm.getPath());
        if (exportRealmFile.exists()) {
            try {
                FileUtil.copy(exportRealmFile, new File(Environment.getExternalStorageDirectory() + "/" + realmDBFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return exportRealmFile;
    }
}

class FileUtil {
    public static void copy(File src, File dst) {
        try {

            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
