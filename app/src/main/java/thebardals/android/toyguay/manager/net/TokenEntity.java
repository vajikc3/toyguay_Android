package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

public class TokenEntity {
    @SerializedName("sucess") private boolean sucess;
    @SerializedName("token") private String token;

    public String getToken(){
        return token;
    }
    public boolean tokenSucess(){
        return sucess;
    }
}
