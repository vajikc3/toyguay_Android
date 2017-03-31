package thebardals.android.toyguay.model.mappers;


import java.util.LinkedList;
import java.util.List;

import thebardals.android.toyguay.manager.net.ToyEntity;
import thebardals.android.toyguay.model.Toy;

public class ToyEntityMapper {
    public List<Toy> map(List<ToyEntity> toyEntities){
        List<Toy> result = new LinkedList<>();

        for (ToyEntity entity: toyEntities){
            Toy toy = new Toy();
            toy.setId(entity.getId());
            toy.setName(entity.getName());
            toy.setDescription(entity.getDescription());
            toy.setPrice(entity.getPrice());
            toy.setState(entity.getState());
            toy.setCategories(entity.getCategories());
            toy.setImageURL(entity.getImageURL());
            result.add(toy);
        }
        return result;
    }

}
