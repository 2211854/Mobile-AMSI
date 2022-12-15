package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaAviaoAdaptador;

public class ListaAvioesFragment extends Fragment {

    String Empresas[] = {"TAP","TAP","TAP"};
    String NomeAviaos[] = {"A380","A380","A380"};
    String Estados[] = {"Aterrado","Em Curso","Em manutenção"};

    private View v;
    ListView listview;
    ListaAviaoAdaptador listaAviaoAdaptador;
    TextView textFilter, tv_titulo;
    ArrayAdapter adapter;
    TextInputLayout til_searchFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_lista_avioes, container, false);

        listview = v.findViewById(R.id.lvAviao);
        listaAviaoAdaptador = new ListaAviaoAdaptador(getContext(), Empresas, Estados,  NomeAviaos);
        listview.setAdapter(listaAviaoAdaptador);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.Fl_menu, new DetalhesAviaoFragment());
                fr.commit();
            }
        });

        ImageButton ib_search_aviao = (ImageButton) v.findViewById(R.id.ib_search_aviao);
        ib_search_aviao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                til_searchFilter = (TextInputLayout) v.findViewById(R.id.searchFilter_aviao);
                tv_titulo = (TextView) v.findViewById(R.id.tv_titulo_lista_aviao);
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

        return v;

    }
}