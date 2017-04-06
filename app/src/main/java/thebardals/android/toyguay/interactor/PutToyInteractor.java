package thebardals.android.toyguay.interactor;


import android.content.Context;

import thebardals.android.toyguay.manager.net.NetworkManager;
import thebardals.android.toyguay.model.IAuthenticate;
import thebardals.android.toyguay.model.Token;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.util.Constants;

public class PutToyInteractor {
    public interface PutToyInteractorResponse{
        public void response(int error);
        public void data(String id);
    }
    public void execute(final Context context, final Toy toy,final PutToyInteractorResponse response){
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.putToyToServer(toy, new NetworkManager.PutToyListener() {
            @Override
            public void putToySucess(String id) {
                if (response != null){
                    response.data(id);
                }
            }
            @Override
            public void putToyDidFail(int errorCode) {
                if (errorCode == Constants.POST_TOY_ERROR_AUTH){
                    Token token = new Token(context);
                    token.getTokenFromServer(new IAuthenticate.DownloadTokenFromServer() {
                        @Override
                        public void getTokenSucess(String token) {
                            PutToyInteractor.this.execute(context, toy, new PutToyInteractorResponse() {
                                @Override
                                public void response(int error) {
                                    if (response!=null){
                                        response.response(error);
                                    }
                                }

                                @Override
                                public void data(String id) {
                                    if (response!=null){
                                        response.data(id);
                                    }
                                }
                            });
                        }

                        @Override
                        public void getTokenError() {
                            if (response!=null){
                                response.response(Constants.POST_TOY_ERROR_AUTH);
                            }
                        }
                    });

                }
                else{
                    if (response != null){
                        response.response(errorCode);
                    }
                }
            }
        });
    }
}
