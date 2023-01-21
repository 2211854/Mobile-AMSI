package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.mjrammobile.MainActivity;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaTarefasFragment;
import pt.ipleiria.estg.dei.mjrammobile.listeners.LoginListener;
import pt.ipleiria.estg.dei.mjrammobile.listeners.TarefasListener;
import pt.ipleiria.estg.dei.mjrammobile.listeners.VoosListener;
import pt.ipleiria.estg.dei.mjrammobile.utils.VooJsonParser;


public class Singleton {

    private static  Singleton instance = null;
    private static RequestQueue volleyQueue = null;
    private String token;
    private static String mUrlAPIVoos ="http://10.0.2.2:80/WEB-PSI-SIS/mjram/backend/web/api/voo/allvoo?access-token=";
    private static final String mUrlAPILogin = "http://10.0.2.2:80/WEB-PSI-SIS/mjram/backend/web/api/auth/login";
    //private static final String mUrlAPILogin = "http://amsi.dei.estg.ipleiria.pt/api/auth/login";
    private LoginListener loginListener;
    private VoosListener voosListener;
    private TarefasListener tarefasListener;

    private ArrayList<Voo> voos;
    private VooBDHelper vooDb;
    private ArrayList<Aviao> aviaos;
    private AviaoDBHelper aviaoDb;
    private ArrayList<Tarefa> tarefas;
    private TarefaDBHelper tarefaDb;
    private ArrayList<Ocupacao> ocupacoes;
    private OcupacaoDBHelper ocupacaoDB;
    private PerfilDBHelper perfilDB;
    private Perfil perfil;

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
    public void setTarefasListener(TarefasListener tarefasListener) {
        this.tarefasListener = tarefasListener;
    }

    public void setVoosListener(VoosListener voosListener) { // para informar a vista
        this.voosListener = voosListener;
    }

    private Singleton(Context context){

        voos = new ArrayList<>();
        vooDb = new VooBDHelper(context);
        aviaos = new ArrayList<>();
        aviaoDb = new AviaoDBHelper(context);
        tarefas = new ArrayList<>();
        tarefaDb = new TarefaDBHelper(context);
        ocupacoes = new ArrayList<>();
        ocupacaoDB = new OcupacaoDBHelper(context);
        perfilDB = new PerfilDBHelper(context);

    }

    public void loginAPI(final String username, final String password, final Context context){
        if (!VooJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        }else
        {
            StringRequest req = new StringRequest(Request.Method.POST, mUrlAPILogin, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    String token = VooJsonParser.parserJsonLogin(response);
                    if (loginListener != null)
                        loginListener.onValidateLogin(token, username, context);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("LOGIN: Error" + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    //params.put("username", username);
                    //params.put("password", password);
                    String creds = String.format("%s:%s",username,password);
                    String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                    params.put("Authorization", auth);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    public void getAllVoosAPI(final Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MainActivity.TOKEN, null);
        mUrlAPIVoos = mUrlAPIVoos + token;
        if (!VooJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
            if (voosListener!=null)
            {
                voosListener.onRefreshListaVoos(vooDb.getAllVooBD());
            }
        }else
        {
            JsonArrayRequest req=new JsonArrayRequest(Request.Method.GET, mUrlAPIVoos, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    voos = VooJsonParser.parserJsonVoos(response);
                    adicionarVoosBD(voos);

                    if (voosListener!=null)
                    {
                        voosListener.onRefreshListaVoos(voos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getAllTarefasAPI(final Context context){
        if (!VooJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
            if (tarefasListener !=null)
            {
                tarefasListener.onRefreshListaTarefas(tarefaDb.getAllTarefaBD());
            }
        }else
        {
            JsonArrayRequest req=new JsonArrayRequest(Request.Method.GET, mUrlAPILogin, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    tarefas = VooJsonParser.parserJsonTarefas(response);
                    adicionarTarefasBD(tarefas);

                    if (tarefasListener!=null)
                    {
                        tarefasListener.onRefreshListaTarefas(tarefas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            volleyQueue.add(req);
        }
    }

    /*public void adicionarLivroAPI(final Tarefa tarefa , final Context context, String token){
        if (!VooJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        }else
        {
            StringRequest req = new StringRequest(Request.Method.POST, mUrlAPILogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    adicionarTarefaBD(VooJsonParser.parserJsonTarefa(response));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    //params.put("token", token);
                    params.put("id_voo", tarefa.getId_voo()+"");// Transformar em string
                    params.put("id_hangar", tarefa.getId_hangar()+"");
                    params.put("id_recurso", tarefa.getId_recurso()+"");
                    params.put("designacao", tarefa.getDesignacao());
                    params.put("estado", tarefa.getEstado());
                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }*/

    //buscar tudo a base dados voo
    public ArrayList<Voo> getVoosBD() { // return da copia dos Voos
        voos=vooDb.getAllVooBD();
        return new ArrayList(voos);
    }

    //buscar tudo a base dados perfil
    public Perfil getPerfilBD() { // return da copia dos Voos
        perfil=perfilDB.getAllPerfilBD();
        return perfil;
    }

    public Voo getVoo(int idVoo){
        for (Voo V : voos){
            if(V.getId() == idVoo)
                return V;
        }
        return null;
    }
    //buscar tudo a base dados Tarefa
    public ArrayList<Tarefa> getTarefasBD() { // return da copia dos Tarefas
        tarefas=tarefaDb.getAllTarefaBD();
        return new ArrayList(tarefas);
    }

    public Tarefa getTarefa(int idTarefa){
        for (Tarefa T : tarefas){
            if(T.getId() == idTarefa)
                return T;
        }
        return null;
    }

    //buscar tudo a base dados Aviao
    public ArrayList<Aviao> getAviaosBD() { // return da copia dos Aviao
        for (Aviao aviao: aviaoDb.getAllAviaoBD()){
            aviao.setOcupacoes(ocupacaoDB.getOcupacaoDB(aviao.getId()));
            aviaos.add(aviao);

        }
        return new ArrayList(aviaos);
    }

    public Aviao getAviao(int idAviao){
        for (Aviao A : aviaos){
            if(A.getId() == idAviao)
                return A;
        }
        return null;
    }

    //Adicionar base dados voo
    public void adicionarVoosBD(ArrayList<Voo> voos)
    {
        vooDb.removerAllVoo();
        for(Voo V:voos)
        {
            adicionarVooBD(V);
        }
    }
    public void adicionarVooBD(Voo v) {vooDb.adicionarVooBD(v);}

    //Adicionar base dados Tarefa
    public void adicionarTarefasBD(ArrayList<Tarefa> tarefas)
    {
        tarefaDb.removerAllTarefa();
        for(Tarefa T:tarefas)
        {
            adicionarTarefaBD(T);
        }
    }
    public void adicionarTarefaBD(Tarefa T) {tarefaDb.adicionarTarefaBD(T);}

    //Adicionar base dados Aviao
    public void adicionarAviaosBD(ArrayList<Aviao> aviaos)
    {
        aviaoDb.removerAllAviao();
        for(Aviao A:aviaos)
        {
            adicionarAviaoBD(A);
        }
    }

    public void adicionarAviaoBD(Aviao A) {aviaoDb.adicionarAviaoBD(A);}

    //remover Tarefa
    public void removerTarefaBD(int id)
    {
        Tarefa auxTarefa = getTarefa(id);
        if (auxTarefa!=null)
            tarefaDb.removerTarefaBD(id);
    }

}
