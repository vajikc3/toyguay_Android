package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

public class ToyPostResponse {
    @SerializedName("sucess") private boolean sucess;
    @SerializedName("toy") private ToyEntity toy;

    public boolean isSucess() {
        return sucess;
    }

    public ToyEntity getToy() {
        return toy;
    }

}
