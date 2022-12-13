package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pt.ipleiria.estg.dei.mjrammobile.R;

public class ListaAviaoAdaptador extends BaseAdapter {

    Context ctx;
    String Empresa[];
    String NomeAviao[];
    String Estado[];
    LayoutInflater inflater;

    public ListaAviaoAdaptador (Context context, String [] Empresas, String [] Estados, String [] NomeAviaos ){
        this.ctx = context;
        this.Empresa = Empresas;
        this.NomeAviao = NomeAviaos;
        this.Estado = Estados;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return NomeAviao.length;
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
        view = inflater.inflate(R.layout.activity_custom_lista_aviao, null);

        //define as textviews
        TextView tv_nome_aviao = (TextView) view.findViewById(R.id.tv_Titulo_aviao);
        TextView tv_empresas = (TextView) view.findViewById(R.id.tv_empresa);
        TextView tv_estados= (TextView) view.findViewById(R.id.tv_estado);

        //envia para as textview as variaveis
        tv_empresas.setText(Empresa[i]);
        tv_estados.setText(Estado[i]);
        tv_nome_aviao.setText(NomeAviao[i]);

        return view;
    }
}
