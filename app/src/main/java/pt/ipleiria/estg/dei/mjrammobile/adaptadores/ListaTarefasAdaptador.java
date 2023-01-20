package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;

public class ListaTarefasAdaptador extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    private ArrayList<Tarefa> tarefas;

    public ListaTarefasAdaptador (Context context, ArrayList<Tarefa> tarefas ){
        this.ctx = context;
        this.tarefas = tarefas;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return tarefas.size();
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
        if(inflater == null)
            inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view = inflater.inflate(R.layout.activity_custom_lista_tarefas, null);

        //envia para as textview as variaveis
        ViewHolderListaTarefas viewHolder = (ViewHolderListaTarefas) view.getTag();
        if(viewHolder == null)
        {
            viewHolder = new ViewHolderListaTarefas(view);
            view.setTag(viewHolder);
        }

        viewHolder.update(tarefas.get(i));

        return view;
    }

    private class ViewHolderListaTarefas{
        private TextView tv_designacao, tv_estados;

        public ViewHolderListaTarefas(View view){
            tv_designacao = view.findViewById(R.id.tv_Lista_Tarefas_Designacao);
            tv_estados = view.findViewById(R.id.tv_Lista_Tarefas_estado);
        }

        public void update(Tarefa tarefas){
            tv_designacao.setText(tarefas.getDesignacao());
            tv_estados.setText(tarefas.getEstado());

        }
    }
}
