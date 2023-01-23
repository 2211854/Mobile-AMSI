package pt.ipleiria.estg.dei.mjrammobile.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.AviaoListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Aviao;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.utils.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalhesAviaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalhesAviaoFragment extends Fragment implements AviaoListener {
    private int id_voo;
    private TextView tv_combustivel, tv_first_Classe, tv_Business, tv_Economica;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalhesAviaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalhesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalhesAviaoFragment newInstance(String param1, String param2) {
        DetalhesAviaoFragment fragment = new DetalhesAviaoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        id_voo = arguments.getInt("ID_VOO");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalhes_aviao, container, false);

        tv_combustivel = v.findViewById(R.id.tv_combustivel);
        tv_first_Classe = v.findViewById(R.id.tv_first_classe);
        tv_Business = v.findViewById(R.id.tv_Business);
        tv_Economica = v.findViewById(R.id.tv_Economica);

        Button btnListaTarefa = (Button)v.findViewById(R.id.btnListaTarefas);

        btnListaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!JsonParser.isConnectionInternet(getContext())){
                    Toast.makeText(getContext(), "Sem ligação à internet", Toast.LENGTH_LONG).show();
                }else {
                    ListaTarefasFragment fragment = new ListaTarefasFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt("ID_VOO", id_voo);
                    fragment.setArguments(arguments);

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.Fl_menu, fragment);
                    fr.commit();
                }
            }
        });

        Singleton.getInstance(getContext()).setAviaoListener(this);
        Singleton.getInstance(getContext()).getAviaoAPI(id_voo, getContext());

        return v;
    }

    @Override
    public void onRefreshAviao(Aviao aviao) {
        if(aviao != null){
            float percentagem = 100*aviao.getCombustivelatual()/aviao.getCombustivelmaximo();
            tv_combustivel.setText(aviao.getCombustivelatual() + "(" + percentagem + "% )");
            tv_first_Classe.setText(aviao.getOcupacaoprimeira() + " ");
            tv_Business.setText(aviao.getOcupacaobusiness() + " ");
            tv_Economica.setText(aviao.getOcupacaoeconomica() + " ");
        }
    }
}