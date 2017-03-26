package thebardals.android.toyguay.model;

import java.io.Serializable;
import java.util.Date;


/**
 * AKI --> ¡CUIDADINNNN! No es la clase Toy real. La uso mientras no se quite Realm de la clase "buena" en thebardals.domain
 */

public class Toy implements Serializable {
    private long id;

    private String name;
    private String description;
    private String state;
    private double price;
    private String imageURL;
    private long idUser;
    private Date createdAt;
    private Date updatedAt;
    //private List<Category> categories = new RealmList<Category>();


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
        return idUser;
    }

    public void setIdSeller(long idSeller) {
        this.idUser = idUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
