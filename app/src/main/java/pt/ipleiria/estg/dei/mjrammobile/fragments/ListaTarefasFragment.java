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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.mjrammobile.R;

public class ListaTarefasFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_tarefas, container, false);

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