package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

import java.util.List;

class LocationEntity {
    @SerializedName("type") private String type;
    @SerializedName("coordinates") private List<Double> coordinates;

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
}
