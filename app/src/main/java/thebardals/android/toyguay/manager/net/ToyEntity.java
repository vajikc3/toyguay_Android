package thebardals.android.toyguay.manager.net;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ToyEntity {
    @SerializedName("_id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("price") private double price;
    @SerializedName("categories") private List<String> categories;
    @SerializedName("imageURL") private List<String> imageURL;
    @SerializedName("state") private String state;
    @SerializedName("udatedAt") private Date updatedAt;
    @SerializedName("createdAt") private Date createdAt;



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public String getState() {
        return state;
    }
}
