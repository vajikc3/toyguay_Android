package thebardals.android.toyguay.util;

/**
 * Created by jacobo on 6/2/17.
 */

public class Constants {
    public static final String IMAGES = "images" ;
    public static final String IMAGE_URL = "url";
    public static final String IMAGE_TOY_ID = "toyid";
    public static String appName = "thebardals.android.toyguay";
    public static final String INTENT_KEY_TOY_DETAIL = appName + ".INTENT_KEY_TOY_DETAIL";
    // Backend data
    //public static final String BACKEND_URL="http://192.168.2.140:3000/api/v1/";
    public static final String BACKEND_URL="http://www.toyguay.com/api/v1/";
    //public static final String BACKEND_URL="http://192.168.1.34:3000/api/v1/";
    public static final String AUTHENTICATE="users/authenticate";
    public static final String USER="user";
    public static final String PASS="password";
    public static final String EMAIL="email";
    public static final String TOYS="toys";
    public static final String ALTERNATIVE_NO_IMAGE="http://placehold.it/250x250?text=Sin foto";
    public static final String GET_TOKEN="?token=";
    /* Toy POST data */
    public static final String TOY_NAME="name";
    public static final String TOY_DESCRIPTION="description";
    public static final String TOY_PRICE="price";
    public static final String TOY_CATEGORIES="categories";
    public static final int POST_TOY_OK = 0;
    public static final int POST_TOY_ERROR_AUTH=403;

    public static final String AZURE_CONTAINER_NAME = "toyguay-image-container";
    public static final String AZURE_STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=toyguay;AccountKey=shwGPxWpIVvnxkcVmRz1p8JqOlcG7YXMpnXULTM8bsdT+kLe9dBuzQi2K+XnVCittjLf7/lWJfQj5FtyAlChOQ==;EndpointSuffix=core.windows.net";
    public static final String AZURE_URL_BASE = "https://toyguay.blob.core.windows.net/toyguay-image-container/";

}
