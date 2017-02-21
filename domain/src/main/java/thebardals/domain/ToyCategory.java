package thebardals.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jacobo on 21/2/17.
 */

public class ToyCategory extends RealmObject {
    @PrimaryKey
    private long idToyIdCategory;

    @Required
    private String name;

    public long getIdToyIdCategory() {
        return idToyIdCategory;
    }

    public void setIdToyIdCategory(long idToyIdCategory) {
        this.idToyIdCategory = idToyIdCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
