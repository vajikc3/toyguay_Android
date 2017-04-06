package thebardals.android.toyguay.interactor;


import android.support.annotation.NonNull;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import thebardals.android.toyguay.util.Constants;

import static thebardals.android.toyguay.util.Constants.AZURE_URL_BASE;

public class UploadImageToAzureInteractor {
    public interface UploadImageToAzureResponse {
        public void sucess(String url);

        public void didFail();
    }

    public void execute(@NonNull final File file, final UploadImageToAzureResponse response) {
        new Thread( new Runnable() {

            private void uploadImage(){
                try {
                    CloudStorageAccount storageAccount = CloudStorageAccount.parse(Constants.AZURE_STORAGE_CONNECTION_STRING);

                    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
                    CloudBlobContainer container = blobClient.getContainerReference(Constants.AZURE_CONTAINER_NAME);

                    BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
                    containerPermissions.setPublicAccess(BlobContainerPublicAccessType.BLOB);
                    container.uploadPermissions(containerPermissions);

                    final String uniqueID = UUID.randomUUID().toString() + ".jpg";
                    CloudBlockBlob blob = container.getBlockBlobReference(uniqueID);

                    blob.upload(new FileInputStream(file), file.length());

                    if (response != null) {
                        response.sucess(AZURE_URL_BASE + uniqueID);
                    }
                } catch (Exception e) {
                    if (response != null) {
                        response.didFail();
                    }
                    // Output the stack trace.
                    e.printStackTrace();
                }
            }


            @Override
            public void run() {
                uploadImage();
            }
        }).start();


    }
}
