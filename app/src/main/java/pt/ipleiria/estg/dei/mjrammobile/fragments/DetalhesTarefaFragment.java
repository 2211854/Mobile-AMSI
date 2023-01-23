package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.MainActivity;
import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.TarefaSingleListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.modelo.TarefaSingle;

public class DetalhesTarefaFragment extends Fragment implements TarefaSingleListener {
    private int id_tarefa,id_voo;
    ArrayList<String> estados;
    Spinner spinner;
    ArrayAdapter adapter;
    private TarefaSingle tarefaSingle;
    Button btnEliminar;
    private String username, username2;
    SharedPreferences sharedPreferences;

    private FloatingActionButton fabGuardar;

    private TextView tv_designacao, tv_recurso, tv_hangar, tv_data_registo, tv_data_inicio, tv_data_final;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        id_tarefa = arguments.getInt("ID_TAREFA");
        id_voo = arguments.getInt("ID_VOO");
        sharedPreferences = getContext().getSharedPreferences(MainActivity.SHARED_USER, Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalhes_tarefa, container, false);

        tv_designacao = v.findViewById(R.id.tv_tarefa_designacao);
        tv_recurso = v.findViewById(R.id.tv_tarefa_Recurso);
        tv_hangar = v.findViewById(R.id.tv_tarefa_hangar);
        tv_data_registo = v.findViewById(R.id.tv_tarefa_registo);
        tv_data_inicio = v.findViewById(R.id.tv_tarefa_inicio);
        tv_data_final = v.findViewById(R.id.tv_tarefa_final);
        fabGuardar=v.findViewById(R.id.FB_alterar_tarefas);



        estados = new ArrayList<String>();
        estados.add("planeada");
        estados.add("concluido");
        estados.add("cancelado");

        spinner = v.findViewById(R.id.sp_tarefa_estado);

        adapter= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, estados);
        spinner.setAdapter(adapter);




        Button btnListaTarefa = (Button)v.findViewById(R.id.btnListaTarefas);

        btnEliminar = (Button)v.findViewById(R.id.btnTarefaEliminar);

        btnListaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaTarefasFragment fragment = new ListaTarefasFragment();
                Bundle arguments = new Bundle();
                arguments.putInt("ID_VOO", id_voo);
                fragment.setArguments(arguments);

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.Fl_menu, fragment);
                fr.commit();
            }
        });



        Singleton.getInstance(getContext()).setTarefaSingleListener(this);
        Singleton.getInstance(getContext()).getTarefaSingleAPI(id_tarefa, getContext());

        return v;
    }

    @Override
    public void onRefreshTarefaSingle(TarefaSingle TarefaSingle) {
        if(TarefaSingle != null){
            tv_designacao.setText(TarefaSingle.getDesignacao());
            tv_recurso.setText(TarefaSingle.getRecurso());
            tv_hangar.setText(TarefaSingle.getHangar());
            tv_data_registo.setText(TarefaSingle.getData_registo());
            tv_data_inicio.setText(TarefaSingle.getData_inicio());
            tv_data_final.setText(TarefaSingle.getData_final());

            spinner.setSelection(adapter.getPosition(TarefaSingle.getEstado()));



            username = sharedPreferences.getString(MainActivity.USERNAME, null);
            username2 = TarefaSingle.getUsername_funcionario_registo();
//            System.out.println(username);
//            System.out.println(username2);
            if(username2.equals(username)) {
                btnEliminar.setVisibility(View.VISIBLE);
            }else {
                btnEliminar.setVisibility(View.GONE);
            }

            fabGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TarefaSingle.setEstado(spinner.getSelectedItem().toString());
                    Singleton.getInstance(getContext()).editarTarefaSingleAPI(TarefaSingle, getContext());
                }

            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Eliminar tarefa!")
                            .setMessage("Tem a certeza que pretende eliminar esta tarefa?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {


                                    Singleton.getInstance(getContext()).removerTarefaSingleAPI(TarefaSingle, getContext());
                                    ListaTarefasFragment fragment = new ListaTarefasFragment();
                                    Bundle arguments = new Bundle();
                                    arguments.putInt("ID_VOO", id_voo);
                                    fragment.setArguments(arguments);

                                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                                    fr.replace(R.id.Fl_menu, fragment);
                                    fr.commit();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Nao fazer nada
                                }
                            })
                            .setIcon(android.R.drawable.ic_delete)
                            .show();
                }
            });

        }
    }
}