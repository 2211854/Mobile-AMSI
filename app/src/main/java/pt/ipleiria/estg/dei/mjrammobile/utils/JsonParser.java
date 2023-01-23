package pt.ipleiria.estg.dei.mjrammobile.utils;

    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;

    import pt.ipleiria.estg.dei.mjrammobile.modelo.Aviao;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Hangar;

    import pt.ipleiria.estg.dei.mjrammobile.modelo.Perfil;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Recurso;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.TarefaSingle;
    import pt.ipleiria.estg.dei.mjrammobile.modelo.Voo;


public class JsonParser {



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
                String quantidade = tarefa.getString("quantidade");
                Tarefa auxTarefa = new Tarefa(id, id_voo, id_hangar, id_recurso, estado, designacao, quantidade);
                tarefas.add(auxTarefa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tarefas;
    }


    public static Perfil parserJsonPerfil(JSONObject response){
        JSONObject perfil = (JSONObject) response;
        try {
            String nib = perfil.getString("nib");
            String email = perfil.getString("email");
            String telemovel = perfil.getString("telemovel");
            String nomes = perfil.getString("nomes");
            String dataregisto = perfil.getString("dataregisto");
            return new Perfil(nib, email, telemovel, nomes, dataregisto,1);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Aviao parserJsonAviao(JSONObject response) {
        Aviao aviao = null;
        try {
                int id = response.getInt("id");
                int combustivelatual = response.getInt("combustivelatual");
                int combustivelmaximo = response.getInt("combustivelmaximo");
                int ocupacaoeconomica = response.getInt("ocupacaoEconomica");
                int ocupacaoprimeira = response.getInt("ocupacaoPrimeira");
                int ocupacaobusiness = response.getInt("ocupacaoBusiness");
                aviao = new Aviao(id, combustivelatual, combustivelmaximo, ocupacaoeconomica, ocupacaoprimeira, ocupacaobusiness);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aviao;
    }

    public static TarefaSingle parserJsonTarefaSingle(JSONObject response) {
        TarefaSingle tarefaSingle = null;
        try {
            int id = response.getInt("id");
            int id_voo = response.getInt("id_voo");
            String username_funcionario_registo = response.getString("username_funcionario_registo");
            String designacao = response.getString("designacao");
            String hangar = response.getString("id_hangar");
            String recurso = response.getString("id_recurso");
            String estado = response.getString("estado");
            String quantidade = response.getString("quantidade");
            String data_registo = response.getString("data_registo");
            String data_inicio = response.getString("data_inicio");
            String data_final = response.getString("data_conclusao");

            tarefaSingle = new TarefaSingle(id ,id_voo,username_funcionario_registo, designacao,estado,recurso,  hangar,  quantidade,data_registo,data_inicio,data_final);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tarefaSingle;
    }


    /*public static ArrayList<Ocupacao> parserJsonOcupacoes(JSONObject response) {
        ArrayList<Ocupacao> ocupacoes = new ArrayList<>();
        int ocupacao;
        String classe;
        Ocupacao auxOcupacao;
        try {
            ocupacao = response.getInt("Economica");
            classe = response.getString("classe");
            auxOcupacao = new Ocupacao(1, ocupacao,"Economica");
            ocupacoes.add(auxOcupacao);

            ocupacao = response.getInt("Primeira");
            classe = response.getString("classe");
            auxOcupacao = new Ocupacao(2, ocupacao,"Primeira");
            ocupacoes.add(auxOcupacao);

            ocupacao = response.getInt("Business");
            classe = response.getString("classe");
            auxOcupacao = new Ocupacao(2, ocupacao,"Business");
            ocupacoes.add(auxOcupacao);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ocupacoes;
    }*/


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
                Hangar auxHangar = new Hangar(id, designacao);
                hangares.add(auxHangar);
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
            String quantidade = tarefa.getString("quantidade");
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
