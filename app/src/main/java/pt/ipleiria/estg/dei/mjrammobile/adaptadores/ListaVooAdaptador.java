package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Voo;

public class ListaVooAdaptador extends BaseAdapter {

    Context ctx;
    private ArrayList<Voo> voos;
    LayoutInflater inflater;


    public ListaVooAdaptador(Context context, ArrayList<Voo> voos ){
        this.ctx = context;
        this.voos = voos;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {return voos.size();}

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
            view = inflater.inflate(R.layout.activity_custom_lista_voo, null);

        //envia para as textview as variaveis
        ListaVooAdaptador.ViewHolderListaVoos viewHolder = (ListaVooAdaptador.ViewHolderListaVoos) view.getTag();
        if(viewHolder == null)
        {
            viewHolder = new ListaVooAdaptador.ViewHolderListaVoos(view);
            view.setTag(viewHolder);
        }



        return view;
    }
    private class ViewHolderListaVoos{
        private TextView tv_designacao, tv_estado, tv_aviao, tv_companhia, tv_pista;


        public ViewHolderListaVoos(View view){
            tv_designacao = view.findViewById(R.id.tv_custom_lista_voo_designacao);
            tv_aviao = view.findViewById(R.id.tv_custom_lista_voo_aviao);
            tv_estado = view.findViewById(R.id.tv_custom_lista_voo_estado);
            tv_pista = view.findViewById(R.id.tv_custom_lista_voo_pista);
            tv_companhia = view.findViewById(R.id.tv_custom_lista_voo_companhia);
        }

        public void update(Voo voos){
            tv_designacao.setText(voos.getDesignacao());
            tv_estado.setText(voos.getEstado());
            tv_aviao.setText(voos.getAviao());
            tv_pista.setText(voos.getPista());
            tv_companhia.setText(voos.getCompanhia());
        }
    }
}
