package thebardals.android.toyguay.model;


import android.content.Context;
import android.content.SharedPreferences;

import thebardals.android.toyguay.interactor.GetTokenInteractor;
import thebardals.android.toyguay.util.Constants;

public class Token implements IAuthenticate {

    private static final String PREFERENCE_USER = "AUTH_USER" ;
    private static final String PREFERENCE_PASSWORD = "AUTH_PASSWORD";
    private static final String PREFERENCE_TOKEN = "AUTH_TOKEN";

    public static String user=null;
    public static String password=null;
    public static String token = null;

    private Context context;

    public Token(final Context context){
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.appName,Context.MODE_PRIVATE);
        user = sharedPreferences.getString(PREFERENCE_USER,null);
        password = sharedPreferences.getString(PREFERENCE_PASSWORD,null);
        token = sharedPreferences.getString(PREFERENCE_TOKEN,null);
    }

    @Override
    public void setUser(String user) {
        Token.user=user;
    }

    @Override
    public String getUser() {
        return Token.user;
    }

    @Override
    public void setPassword(String password) {
        Token.password = password;
    }

    @Override
    public String getPassword() {
        return Token.password;
    }

    @Override
    public void setToken(String token) {
        Token.token = token;
    }

    @Override
    public String getToken() {
        return Token.token;
    }

    @Override
    public void saveUserPass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.appName,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(PREFERENCE_USER,user).apply();
        sharedPreferences.edit().putString(PREFERENCE_PASSWORD,password).apply();
        sharedPreferences.edit().putString(PREFERENCE_TOKEN,token).apply();
    }

    @Override
    public void getTokenFromServer(final DownloadTokenFromServer listener) {
        new GetTokenInteractor().execute(context, null, user, password, new GetTokenInteractor.GetTokenInteractorResponse() {
            @Override
            public void response(String token) {
                if (token!=null){
                    setToken(token);
                    setPassword(password);
                    setUser(user);
                    saveUserPass();
                    if (listener != null){
                        listener.getTokenSucess(token);
                    }
                }
                else{
                    if (listener != null){
                        listener.getTokenError();
                    }
                }

            }
        });
    }
}
