package pt.ipleiria.estg.dei.mjrammobile.utils;

    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;

    import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Voo;


public class VooJsonParser {



    public static ArrayList<Voo> parserJsonVoos(JSONArray response) {
        ArrayList<Voo> voos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject voo = (JSONObject) response.get(i);
                int id = voo.getInt("id");
                String designacao = voo.getString("designacao");
                String estado = voo.getString("estado");
                int id_aviao = voo.getInt("id_aviao");
                int totalbilhetes = voo.getInt("totalbilhetes");
                String aviao = voo.getString("aviao");
                String pista = voo.getString("pista");
                String companhia = voo.getString("companhia");
                    Voo auxVoo = new Voo(id, designacao, estado, id_aviao, totalbilhetes, pista, aviao, companhia);
                voos.add(auxVoo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return voos;
    }

    /*public static ArrayList<Tarefa> parserJsonTarefas(JSONArray response) {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject tarefa = (JSONObject) response.get(i);
                int id = tarefa.getInt("id");
                int id_voo = tarefa.getInt("id_voo");
                int id_hangar = tarefa.getInt("id_hangar");
                int id_recurso = tarefa.getInt("id_recurso");
                String designacao = tarefa.getString("designacao");
                String estado = tarefa.getString("estado");
                Tarefa auxTarefa = new Tarefa(id, id_voo, id_hangar, id_recurso, estado, designacao);
                tarefas.add(auxTarefa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tarefas;
    }*/


    public static String parserJsonLogin(String response) { // static para nao ter de fazer new
        String token = null;
        try {
            JSONObject login = new JSONObject(response);
            token = login.getString("auth_key");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

}
