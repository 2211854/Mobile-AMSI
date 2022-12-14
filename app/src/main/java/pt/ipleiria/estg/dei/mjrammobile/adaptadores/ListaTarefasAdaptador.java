package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pt.ipleiria.estg.dei.mjrammobile.R;

public class ListaTarefasAdaptador extends BaseAdapter {

    Context ctx;
    String Designacao[];
    String Estado[];
    LayoutInflater inflater;

    public ListaTarefasAdaptador (Context context, String [] Designacoes, String [] Estados ){
        this.ctx = context;
        this.Designacao = Designacoes;
        this.Estado = Estados;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return Designacao.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //view infalter
        view = inflater.inflate(R.layout.activity_custom_lista_tarefas, null);

        //define as textviews
        TextView tv_designacao = (TextView) view.findViewById(R.id.tv_Lista_Tarefas_Designacao);
        TextView tv_estados= (TextView) view.findViewById(R.id.tv_Lista_Tarefas_estado);

        //envia para as textview as variaveis
        tv_designacao.setText(Designacao[i]);
        tv_estados.setText(Estado[i]);

        return view;
    }
}
