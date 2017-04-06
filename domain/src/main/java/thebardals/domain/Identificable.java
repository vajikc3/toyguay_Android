package thebardals.domain;

/**
 * Created by jacobo on 7/3/17.
 */

public interface Identificable<E> {
    public long getId();

    public E setId(long id);
}