package thebardals.android.toyguay.interactor;


import android.content.Context;

import thebardals.android.toyguay.manager.net.NetworkManager;
import thebardals.android.toyguay.manager.net.TokenEntity;

public class GetTokenInteractor {
    public interface GetTokenInteractorResponse{
        public void response(String token);
    }
    public void execute(final Context context, final String user, final String email, final String pass,
                        final GetTokenInteractorResponse response){
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getTokenFromServer(user, email, pass, new NetworkManager.GetTokenListener() {
            @Override
            public void getTokenSucess(TokenEntity result) {
                if (response != null){
                    response.response(result.getToken());
                }
            }

            @Override
            public void getTokenDidFail() {
                if (response != null){
                    response.response(null);
                }
            }
        });
    }
}
