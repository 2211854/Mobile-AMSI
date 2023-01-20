package pt.ipleiria.estg.dei.mjrammobile.modelo;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.mjrammobile.listeners.LoginListener;
import pt.ipleiria.estg.dei.mjrammobile.utils.VooJsonParser;


public class Singleton {

    private static  Singleton instance = null;
    private static RequestQueue volleyQueue = null;
    private static final String mUrlAPILogin = "http://10.0.2.2:80/WEB-PSI-SIS/mjram/backend/web/api/auth/login";
    //private static final String mUrlAPILogin = "http://amsi.dei.estg.ipleiria.pt/api/auth/login";
    private LoginListener loginListener;

    private ArrayList<Voo> voos;
    private VooBDHelper vooDb;
    private ArrayList<Aviao> aviaos;
    private AviaoDBHelper aviaoDb;
    private ArrayList<Tarefa> tarefas;
    private TarefaDBHelper tarefaDb;
    private ArrayList<Ocupacao> ocupacoes;
    private OcupacaoDBHelper ocupacaoDB;

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
    //buscar tudo a base dados voo
    public ArrayList<Voo> getVoosBD() { // return da copia dos Voos
        voos=vooDb.getAllVooBD();
        return new ArrayList(voos);
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
