package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.mjrammobile.listeners.LoginListener;
import pt.ipleiria.estg.dei.mjrammobile.utils.VooJsonParser;


public class Singleton {

    private static  Singleton instance = null;
    private static RequestQueue volleyQueue = null;
    //private static final String mUrlAPILogin = "http://10.0.2.2:80/WEB-PSI-SIS/mjram/backend/web/api/auth/login";
    private static final String mUrlAPILogin = "http://amsi.dei.estg.ipleiria.pt/api/auth/login";
    private LoginListener loginListener;

    public static synchronized Singleton getInstance(Context context){
        if(instance == null)
        {
            instance = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    private Singleton(Context context){

    }

    public void loginAPI(final String email, final String password, final Context context){
        if (!VooJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        }else
        {
            StringRequest req = new StringRequest(Request.Method.POST, mUrlAPILogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String token = VooJsonParser.parserJsonLogin(response);
                    if (loginListener != null)
                        loginListener.onValidateLogin(token, email, context);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("LOGIN: Error" + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }


}
