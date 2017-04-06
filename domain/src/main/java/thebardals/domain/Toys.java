package thebardals.domain;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Toys {
    List<Toy> toys;

    public static @NonNull Toys build(@NonNull List<Toy> toyList){
        Toys toys = new Toys(toyList);
        if (toyList == null) {
            toys.toys = new ArrayList<>();
        }
        return toys;
    }

    public static @NonNull Toys build(){
        return build(new ArrayList<Toy>());
    }

    private Toys (List<Toy> toys){
        this.toys = toys;
    }

    public long size(){
        return toys.size();
    }

    public Toy get(long index) {
        return toys.get((int) index);
    }

    public List<Toy> all(){
        return toys;
    }

    public void add(Toy toy){
        toys.add(toy);
    }

    public void delete(Toy toy){
        toys.remove(toy);
    }

    public void edit(Toy newToy, long index){
        toys.set((int)index, newToy);
    }

}
