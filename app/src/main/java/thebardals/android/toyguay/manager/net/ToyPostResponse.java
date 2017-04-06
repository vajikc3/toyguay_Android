package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

public class ToyPostResponse {
    @SerializedName("sucess") private boolean sucess;
    @SerializedName("toy") private ToyEntityForPost toy;

    public boolean isSucess() {
        return sucess;
    }

    public ToyEntityForPost getToy() {
        return toy;
    }

}
