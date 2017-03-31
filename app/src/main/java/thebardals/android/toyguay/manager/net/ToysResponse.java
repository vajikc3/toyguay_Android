package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToysResponse {
    @SerializedName("sucess") private boolean sucess;
    @SerializedName("total") private long total;
    @SerializedName("rows") private List<ToyEntity> rows;

    public boolean isSucess() {
        return sucess;
    }

    public long getTotal() {
        return total;
    }

    public List<ToyEntity> getRows() {
        return rows;
    }
}
