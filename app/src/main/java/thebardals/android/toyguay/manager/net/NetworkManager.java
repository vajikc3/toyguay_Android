package thebardals.android.toyguay.manager.net;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thebardals.android.toyguay.util.Constants;

public class NetworkManager {
    public interface GetTokenListener{
        public void getTokenSucess(TokenEntity result);
        public void getTokenDidFail();
    }
    public interface GetToysListener{
        public void getToysSucess(List<ToyEntity> result);
        public void getToyDidFail();
    }

    private WeakReference<Context> context;

    public NetworkManager(Context context){
        this.context = new WeakReference<Context>(context);
    }

    public void getTokenFromServer(final String user, final String email, final String password, final GetTokenListener listener){
        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = Constants.BACKEND_URL+Constants.AUTHENTICATE;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                TokenEntity tokenResponse = parseResponseToken(response);
                if (listener!=null){
                    listener.getTokenSucess(tokenResponse);
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                if (listener != null){
                    listener.getTokenDidFail();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<String, String>();
                if (user!=null) {
                    params.put(Constants.USER, user);
                }
                if (email != null) {
                    params.put(Constants.EMAIL, email);
                }
                params.put(Constants.PASS,password);
                return params;
            }
        };
        queue.add(request);
    }

    private TokenEntity parseResponseToken(String response) {
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            TokenEntity tokenEntity = gson.fromJson(reader, TokenEntity.class);
            return tokenEntity;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void getToysFromServer(final GetToysListener listener){
        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = Constants.BACKEND_URL+Constants.TOYS;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<ToyEntity> toyResponse = parseResponseToy(response);
                if (listener != null){
                    listener.getToysSucess(toyResponse);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null){
                    listener.getToyDidFail();
                }
            }
        });
        queue.add(request);
    }

    private List<ToyEntity> parseResponseToy(String response) {
        List<ToyEntity> result = null;
        try{
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            ToysResponse toyResponse = gson.fromJson(reader,ToysResponse.class);
            result = toyResponse.getRows();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
