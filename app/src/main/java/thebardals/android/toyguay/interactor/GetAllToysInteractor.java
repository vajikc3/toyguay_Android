package thebardals.android.toyguay.interactor;


import android.content.Context;

import java.util.List;

import thebardals.android.toyguay.manager.net.NetworkManager;
import thebardals.android.toyguay.manager.net.ToyEntity;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.model.Toys;
import thebardals.android.toyguay.model.mappers.ToyEntityMapper;

public class GetAllToysInteractor {
    public interface GetAllToysInteractorResponse{
        public void response(Toys toys);
    }
    public void execute(final Context context, final GetAllToysInteractorResponse response){
        Toys toys = null;
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getToysFromServer(new NetworkManager.GetToysListener() {
            @Override
            public void getToysSucess(List<ToyEntity> result) {
                List<Toy> toys = new ToyEntityMapper().map(result);
                if (response != null){
                    response.response(Toys.build(toys));
                }
            }

            @Override
            public void getToyDidFail() {

            }
        });
    }
}
