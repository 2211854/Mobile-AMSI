package pt.ipleiria.estg.dei.mjrammobile.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Aviao;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Ocupacao;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;

public class AviaoAdaptador extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    private ArrayList<Aviao> aviaos;
    private ArrayList<Ocupacao> ocupacoes;


    public AviaoAdaptador (Context context, ArrayList<Aviao> aviaos ) {
        this.ctx = context;
        this.aviaos = aviaos;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return aviaos.size();
    }

    @Override
    public Object getItem(int i) {
        return aviaos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return aviaos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //view infalter
        if(inflater == null)
            inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view = inflater.inflate(R.layout.fragment_detalhes_aviao, null);

        //envia para as textview as variaveis
        AviaoAdaptador.ViewHolderDetalhesAviao viewHolder = (AviaoAdaptador.ViewHolderDetalhesAviao) view.getTag();
        if(viewHolder == null)
        {
            viewHolder = new AviaoAdaptador.ViewHolderDetalhesAviao(view);
            view.setTag(viewHolder);
        }

        viewHolder.update(aviaos.get(i));

        return view;
    }

    private class ViewHolderDetalhesAviao{
        private TextView tv_combustivel, tv_first_Classe, tv_Business, tv_Economica, tv_Premium;

        public ViewHolderDetalhesAviao(View view){
            tv_combustivel = view.findViewById(R.id.tv_combustivel);
            tv_first_Classe = view.findViewById(R.id.tv_first_classe);
            tv_Business = view.findViewById(R.id.tv_Business);
            tv_Economica = view.findViewById(R.id.tv_Economica);
        }

        public void update(Aviao aviaos){
            ocupacoes = aviaos.getOcupacoes();
            tv_combustivel.setText(aviaos.getCombustivelatual());
            for(Ocupacao ocupacao : ocupacoes) {
                if(ocupacao.getClasse() == "Primeira"){
                    tv_first_Classe.setText(ocupacao.getOcupacao());
                }
                if(ocupacao.getClasse() == "Business"){
                    tv_Business.setText(ocupacao.getOcupacao());
                }
                if(ocupacao.getClasse() == "Economica"){
                    tv_Economica.setText(ocupacao.getOcupacao());
                }
            }
        }
    }
}
