package thebardals.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jacobo on 11/2/17.
 */

public class Toy extends RealmObject {
    @PrimaryKey private long id;

    @Required private String name;
    @Required private String description;
    @Required private String state;
    private double price;
    private String imageURL;
    private long idSeller;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(long idSeller) {
        this.idSeller = idSeller;
    }
}
