package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaVooAdaptador;
import pt.ipleiria.estg.dei.mjrammobile.listeners.VoosListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Voo;
import pt.ipleiria.estg.dei.mjrammobile.utils.VooJsonParser;


public class ListaVoosFragment extends Fragment implements VoosListener {


    private View v;
    ListView listview;
    ListaVooAdaptador ListaVooAdaptador;

    private ArrayList<Voo> voos;
    TextView textFilter, tv_titulo;
    ArrayAdapter adapter;
    TextInputLayout til_searchFilter;

    public ListaVoosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_lista_voos, container, false);
        listview = v.findViewById(R.id.lvAviao);

        /*ListaVooAdaptador = new ListaVooAdaptador(getContext(), voos);
        listview.setAdapter(ListaVooAdaptador);*/

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (!VooJsonParser.isConnectionInternet(getContext())){
                    Toast.makeText(getContext(), "Sem ligação à internet", Toast.LENGTH_LONG).show();
                }else {
                    DetalhesAviaoFragment fragment = new DetalhesAviaoFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt("ID_VOO", (int) id);
                    fragment.setArguments(arguments);

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.Fl_menu, fragment);
                    fr.commit();
                }

            }
        });

        ImageButton ib_search_aviao = (ImageButton) v.findViewById(R.id.ib_search_aviao);
        ib_search_aviao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                til_searchFilter = (TextInputLayout) v.findViewById(R.id.searchFilter_aviao);
                tv_titulo = (TextView) v.findViewById(R.id.tv_titulo_lista_voos);
                if(tv_titulo.getVisibility() == v.VISIBLE){
                    tv_titulo.setVisibility(tv_titulo.GONE);
                    til_searchFilter.setVisibility(til_searchFilter.VISIBLE);
                    til_searchFilter.setEnabled(true);
                }else{
                    tv_titulo.setVisibility(tv_titulo.VISIBLE);
                    til_searchFilter.setVisibility(til_searchFilter.GONE);
                    til_searchFilter.setEnabled(false);
                }
            }
        });

        Singleton.getInstance(getContext()).setVoosListener(this);
        Singleton.getInstance(getContext()).getAllVoosAPI(getContext());
        return v;

    }

    @Override
    public void onRefreshListaVoos(ArrayList<Voo> listaVoos) {

        if(listaVoos != null){
            listview.setAdapter(new ListaVooAdaptador(getContext(), listaVoos));
        }
    }
}