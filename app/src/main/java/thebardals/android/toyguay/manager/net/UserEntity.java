package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("_id") private String id;
    @SerializedName("first_name") private String first_name;
    @SerializedName("last_name") private String last_name;
    @SerializedName("nick_name") private String nick_name;
    @SerializedName("email") private String email;
    @SerializedName("province") private String province;
    @SerializedName("city") private String city;
    @SerializedName("country") private String country;
    @SerializedName("admin") private boolean admin;
    @SerializedName("location") private LocationEntity location;

}
