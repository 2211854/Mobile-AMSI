package pt.ipleiria.estg.dei.mjrammobile.utils;

    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;

    import pt.ipleiria.estg.dei.mjrammobile.modelo.Hangar;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Recurso;
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

    public static ArrayList<Tarefa> parserJsonTarefas(JSONArray response) {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject tarefa = (JSONObject) response.get(i);
                int id = tarefa.getInt("id");
                int id_voo = tarefa.getInt("id_voo");
                String id_hangar = tarefa.getString("id_hangar");
                String id_recurso = tarefa.getString("id_recurso");
                String designacao = tarefa.getString("designacao");
                String estado = tarefa.getString("estado");
                int quantidade = tarefa.getInt("quantidade");
                Tarefa auxTarefa = new Tarefa(id, id_voo, id_hangar, id_recurso, estado, designacao, quantidade);
                tarefas.add(auxTarefa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public static ArrayList<Recurso> parserJsonRecursos(JSONArray response) {
        ArrayList<Recurso> recursos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject recurso = (JSONObject) response.get(i);
                int id = recurso.getInt("id");
                String nome = recurso.getString("nome");
                String unidademedida = recurso.getString("unidademedida");
                Recurso auxRecursos = new Recurso(id, nome, unidademedida);
                recursos.add(auxRecursos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recursos;
    }

    public static ArrayList<Hangar> parserJsonHangares(JSONArray response) {
        ArrayList<Hangar> hangares = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject hangar = (JSONObject) response.get(i);
                int id = hangar.getInt("id");
                String designacao = hangar.getString("designacao");
                Hangar auxHangares = new Hangar(id, designacao);
                hangares.add(auxHangares);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hangares;
    }

    public static Tarefa parserJsonTarefa(String response) {
        Tarefa auxTarefas = null;
        try {
            JSONObject tarefa = new JSONObject(response);
            int id = tarefa.getInt("id");
            int id_voo = tarefa.getInt("id_voo");
            String id_hangar = tarefa.getString("id_hangar");
            String id_recurso = tarefa.getString("id_recurso");
            String designacao = tarefa.getString("designacao");
            String estado = tarefa.getString("estado");
            int quantidade = tarefa.getInt("quantidade");
            auxTarefas = new Tarefa(id, id_voo, id_hangar, id_recurso, designacao, estado, quantidade);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxTarefas;
    }


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
