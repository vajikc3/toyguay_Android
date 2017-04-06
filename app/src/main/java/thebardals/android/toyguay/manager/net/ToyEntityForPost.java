package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

public class ToyEntityForPost {
    @SerializedName("_id") private String id;
    @SerializedName("seller") private String seller;

    public String getId(){
        return this.id;
    }
    public String getSeller(){
        return this.seller;
    }
}
