package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import pt.ipleiria.estg.dei.mjrammobile.listeners.HangarListener;
import pt.ipleiria.estg.dei.mjrammobile.listeners.RecursoListener;
import pt.ipleiria.estg.dei.mjrammobile.listeners.TarefasListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Hangar;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Recurso;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Tarefa;

public class AdicionarTarefaFragment extends Fragment implements HangarListener, RecursoListener {

    private View v;
    private int id_voo,quantidade;
    private String recursoId, hangarId;
    Spinner spinner;
    ArrayList<Hangar> hangares;
    ArrayList<Recurso> recursos;
    private EditText ED_designacao, ED_quantidade;
    private Spinner sp_add_recurso, sp_add_hangar;

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
        Singleton.getInstance(getContext()).setHangaresListener(this);
        Singleton.getInstance(getContext()).getAllHangarAPI(getContext());

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adicionar_tarefa, container, false);
        //SPINNER HANGAR
        hangares = Singleton.getInstance(getContext()).getHangaresBD();
        ArrayList<String> hangaresnome = new ArrayList<>();
        for (Hangar recurso:hangares) {
            hangaresnome.add(recurso.getDesignacao());
        }
        //CODIGO PARA ENVIAR TUDO PARA O SPINNER
        spinner = v.findViewById(R.id.sp_add_hangar);

        ArrayAdapter adapter= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, hangaresnome);
        spinner.setAdapter(adapter);

        Singleton.getInstance(getContext()).setRecursosListener(this);
        Singleton.getInstance(getContext()).getAllRecursoAPI(getContext());
        //RECURSO SPINNER
        recursos = Singleton.getInstance(getContext()).getRecursosBD();
        ArrayList<String> recursosnome = new ArrayList<>();
        for (Recurso recurso:recursos) {
            recursosnome.add(recurso.getNome());
        }
        spinner = v.findViewById(R.id.sp_add_recurso);
        ArrayAdapter adapter2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, recursosnome);
        spinner.setAdapter(adapter2);

        Button btnAddTarefa = (Button) v.findViewById(R.id.btnAdicionarTarefa);
        btnAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ED_quantidade = v.findViewById(R.id.ED_quantidade);
                ED_designacao = v.findViewById(R.id.ED_designacao);
                sp_add_recurso = v.findViewById(R.id.sp_add_recurso);
                sp_add_hangar = v.findViewById(R.id.sp_add_hangar);
                quantidade = Integer.parseInt(ED_quantidade.getText().toString());
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
                if(isTarefaValido()) {
                    Tarefa tarefa = new Tarefa(0, id_voo, hangarId, recursoId, "planeada", ED_designacao.getText() + "", quantidade);
                    Singleton.getInstance(getContext()).adicionarTarefaAPI(tarefa, getContext());
                    ListaTarefasFragment fragment = new ListaTarefasFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt("ID_VOO", id_voo);
                    fragment.setArguments(arguments);
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.Fl_menu,fragment);
                    fr.commit();
                }
            }
        });
        return v;
    }

    private boolean isTarefaValido(){
        String designacao = ED_designacao.getText().toString();

        if (designacao.length()<3){
            ED_designacao.setError("Designacao errada");
            return false;
        }
        return true;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRefreshListaHangar(ArrayList<Hangar> hangares) {

    }

    @Override
    public void onRefreshListaRecurso(ArrayList<Recurso> recursos) {

    }
}