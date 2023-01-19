package pt.ipleiria.estg.dei.mjrammobile.utils;

    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;


public class VooJsonParser {


    public static String parserJsonLogin(String response) { // static para nao ter de fazer new
        String token = null;
        System.out.println(response);
        try {
            JSONObject login = new JSONObject(response);
            token = login.getString("auth_key");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(token);
        return token;
    }

    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

}
