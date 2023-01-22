package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.TarefasListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Hangar;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Recurso;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;

public class AdicionarTarefaFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View v;
    private int id_voo;
    private String recursoId, hangarId;
    Spinner spinner;
    ArrayList<Hangar> hangares;
    ArrayList<Recurso> recursos;
    private EditText ED_designacao, ED_quantidade;
    private Spinner sp_add_estado, sp_add_recurso, sp_add_hangar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        id_voo = arguments.getInt("ID_VOO");
        hangares = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Singleton.getInstance(getContext()).getAllHangarAPI(getContext());
        Singleton.getInstance(getContext()).getAllRecursoAPI(getContext());
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adicionar_tarefa, container, false);

        //CODIGO PARA ENVIAR TUDO PARA O SPINNER
        spinner = v.findViewById(R.id.sp_add_hangar);
        ArrayAdapter adapter= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, hangares);
        spinner.setAdapter(adapter);
        /*adapter = ArrayAdapter.createFromResource
                (getContext(), hangares, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/


        Button btnAddTarefa = (Button) v.findViewById(R.id.btnAdicionarTarefa);
        btnAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ED_quantidade = v.findViewById(R.id.ED_quantidade);
                ED_designacao = v.findViewById(R.id.ED_designacao);
                sp_add_recurso = v.findViewById(R.id.sp_add_recurso);
                sp_add_hangar = v.findViewById(R.id.sp_add_hangar);
                int quantidade = Integer.parseInt(ED_quantidade.toString());
                for (Recurso recurso:recursos) {
                    if (recurso.getNome() == sp_add_recurso.getSelectedItem().toString()){
                        recursoId = recurso.getId()+"";
                    }
                }
                for (Hangar hangar:hangares) {
                    if (hangar.getDesignacao() == sp_add_hangar.getSelectedItem().toString()){
                        hangarId = hangar.getId()+"";
                    }
                }
                Tarefa tarefa = new Tarefa(0,id_voo,hangarId,recursoId,null,ED_designacao.getText()+"", quantidade);

                Singleton.getInstance(getContext()).adicionarTarefaAPI(tarefa,id_voo, getContext());
            }
        });


        return v;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}