package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaAviaoAdaptador;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaAvioesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaAvioesFragment extends Fragment {

    String Empresas[] = {"TAP","TAP","TAP"};
    String NomeAviaos[] = {"A380","A380","A380"};
    String Estados[] = {"Aterrado","Em Curso","Em manutenção"};

    private View v;
    ListView listview;

    public ListaAvioesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_lista_avioes, container, false);

        listview = v.findViewById(R.id.lvAviao);
        ListaAviaoAdaptador listaAviaoAdaptador = new ListaAviaoAdaptador(getContext(), Empresas, Estados,  NomeAviaos);
        listview.setAdapter(listaAviaoAdaptador);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.Fl_menu, new DetalhesAviaoFragment());
                fr.commit();
            }
        });

        return v;

    }
}