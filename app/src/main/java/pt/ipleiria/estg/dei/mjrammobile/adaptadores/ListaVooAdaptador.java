package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.ipleiria.estg.dei.mjrammobile.R;

public class ListaVooAdaptador extends BaseAdapter {

    Context ctx;
    String Companhia[];
    String Aviao[];
    String Estado[];
    String Designacao[];
    String Pista[];
    LayoutInflater inflater;


    public ListaVooAdaptador(Context context, String [] Companhias, String [] Estados, String [] Avioes, String[] Pistas, String[] Designacoes ){
        this.ctx = context;
        this.Companhia = Companhias;
        this.Aviao = Avioes;
        this.Estado = Estados;
        this.Pista = Pistas;
        this.Designacao = Designacoes;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return Aviao.length;
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
        view = inflater.inflate(R.layout.activity_custom_lista_voo, null);

        //define as textviews
        TextView tv_designacoes = (TextView) view.findViewById(R.id.tv_custom_lista_voo_designacao);
        TextView tv_companhias = (TextView) view.findViewById(R.id.tv_custom_lista_voo_companhia);
        TextView tv_estados= (TextView) view.findViewById(R.id.tv_custom_lista_voo_estado);
        TextView tv_avioes= (TextView) view.findViewById(R.id.tv_custom_lista_voo_aviao);
        TextView tv_pistas= (TextView) view.findViewById(R.id.tv_custom_lista_voo_pista);

        //envia para as textview as variaveis
        tv_companhias.setText(Companhia[i]);
        tv_estados.setText(Estado[i]);
        tv_avioes.setText(Aviao[i]);
        tv_pistas.setText(Pista[i]);
        tv_designacoes.setText(Designacao[i]);


        return view;
    }
}
