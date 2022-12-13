package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import pt.ipleiria.estg.dei.mjrammobile.R;

public class AdicionarTarefaFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View v;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adicionar_tarefa, container, false);

        //CODIGO PARA ENVIAR TUDO PARA O SPINNER
        spinner = v.findViewById(R.id.sp_add_tarefas);
        adapter = ArrayAdapter.createFromResource
                (getContext(), R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return v;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    //ACABA AQUI O SPINNER
}