package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaTarefasAdaptador;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;
public class ListaTarefasFragment extends Fragment {

    private View v;
    private ListView listview;
    ArrayList<Tarefa> tarefas;
    private TextView tv_titulo;
    private TextInputLayout til_searchFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_lista_tarefas, container, false);

        //Envia para a listview tudo
        listview = v.findViewById(R.id.lvTarefas);
        ListaTarefasAdaptador listaTarefasAdaptador = new ListaTarefasAdaptador(getContext(), tarefas);
        listview.setAdapter(listaTarefasAdaptador);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.Fl_menu, new DetalhesTarefaFragment());
                fr.commit();
            }});

        //Listener para o enviar para outro fragment
        FloatingActionButton FB_Add_tarefa = (FloatingActionButton) v.findViewById(R.id.FB_Add_tarefas);
        FB_Add_tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.Fl_menu, new AdicionarTarefaFragment());
                fr.commit();
            }
        });

        //Verifica se esta o search ou não
        ImageButton ib_search_tarefa = (ImageButton) v.findViewById(R.id.ib_search_tarefas);
        ib_search_tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                til_searchFilter = (TextInputLayout) v.findViewById(R.id.searchFilter);
                tv_titulo = (TextView) v.findViewById(R.id.tv_titulo_tarefas);
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