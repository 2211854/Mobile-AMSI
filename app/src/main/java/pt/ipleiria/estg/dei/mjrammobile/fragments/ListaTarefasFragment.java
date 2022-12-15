package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaAviaoAdaptador;
import pt.ipleiria.estg.dei.mjrammobile.adaptadores.ListaTarefasAdaptador;

public class ListaTarefasFragment extends Fragment {

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
        ListaTarefasAdaptador listaTarefasAdaptador = new ListaTarefasAdaptador(getContext(), Designacoes, Estados);
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



        return v;
    }

}