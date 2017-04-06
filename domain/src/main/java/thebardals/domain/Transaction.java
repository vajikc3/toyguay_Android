package thebardals.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jacobo on 21/2/17.
 */

public class Transaction extends RealmObject {
    @PrimaryKey
    private long id;

    private long idUser;

    private enum type { DONACION, VENTA, COMPRA };

    private enum state { EN_VENTA, VENDIDO,  DONADO };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}

