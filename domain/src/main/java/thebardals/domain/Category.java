package thebardals.domain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jacobo on 21/2/17.
 */

public class Category extends RealmObject {
    @PrimaryKey
    private long id;

    @Required
    private String name;
    private RealmList<Toy> toys = new RealmList<>();

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

    public RealmList<Toy> getToys() {
        return toys;
    }

    public void setToys(RealmList<Toy> toys) {
        this.toys = toys;
    }
}
