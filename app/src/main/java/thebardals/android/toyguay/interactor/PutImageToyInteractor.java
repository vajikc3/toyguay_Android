package thebardals.android.toyguay.interactor;


import android.content.Context;

import thebardals.android.toyguay.manager.net.NetworkManager;
import thebardals.android.toyguay.model.IAuthenticate;
import thebardals.android.toyguay.model.Token;
import thebardals.android.toyguay.util.Constants;

public class PutImageToyInteractor {
    public interface PutImageToyInteractorResponse{
        public void PutImageDidFail(int error);
        public void PutImageSucess();
    }
    public void execute(final Context context, final String urlImage, final String toyid, final PutImageToyInteractorResponse response){
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.putToyImageToServer(toyid, urlImage, new NetworkManager.PutImageToyListener() {
            @Override
            public void putImageToySucess() {
                if (response!=null){
                    response.PutImageSucess();
                }
            }

            @Override
            public void putImageToyFail(int erroCode) {
                if (erroCode == Constants.POST_TOY_ERROR_AUTH){
                    Token token = new Token(context);
                    token.getTokenFromServer(new IAuthenticate.DownloadTokenFromServer() {
                        @Override
                        public void getTokenSucess(String token) {
                            PutImageToyInteractor.this.execute(context, urlImage, toyid, new PutImageToyInteractorResponse() {
                                @Override
                                public void PutImageDidFail(int error) {
                                    if (response!=null){
                                        response.PutImageDidFail(error);
                                    }
                                }

                                @Override
                                public void PutImageSucess() {
                                    if (response != null){
                                        response.PutImageSucess();
                                    }
                                }
                            });
                        }

                        @Override
                        public void getTokenError() {
                            if (response != null){
                                response.PutImageDidFail(Constants.POST_TOY_ERROR_AUTH);
                            }
                        }
                    });
                }
            }
        });

    }

}
