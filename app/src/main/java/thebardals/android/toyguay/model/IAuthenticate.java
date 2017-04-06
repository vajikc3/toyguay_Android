package thebardals.android.toyguay.model;


public interface IAuthenticate {
    void setUser(String user);
    String getUser();
    void setPassword(String password);
    String getPassword();
    void setToken(String token);
    String getToken();
    void saveUserPass();

    void getTokenFromServer(final DownloadTokenFromServer listener);

    public interface DownloadTokenFromServer{
        public void getTokenSucess(String token);
        public void getTokenError();

    }

}
