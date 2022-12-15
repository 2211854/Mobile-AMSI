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
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaAviaoAdaptador;

public class ListaAvioesFragment extends Fragment {

    String Empresas[] = {"TAP","TAP","TAP"};
    String NomeAviaos[] = {"A380","A380","A380"};
    String Estados[] = {"Aterrado","Em Curso","Em manutenção"};

    private View v;
    ListView listview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_lista_avioes, container, false);

        textFilter = v.findViewById(R.id.searchFilter);

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

        adapter = new ArrayAdapter(getContext(), R.layout.fragment_lista_avioes);

        textFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (ListaAvioesFragment.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;

    }
}