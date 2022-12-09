package pt.ipleiria.estg.dei.mjrammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import pt.ipleiria.estg.dei.mjrammobile.databinding.ActivityMainBinding;
import pt.ipleiria.estg.dei.mjrammobile.fragments.AdicionarTarefaFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.DetalhesTarefaFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaAvioesFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaTarefasFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListaAvioesFragment());

        /*Spinner spinner = findViewById(R.id.sp_add_tarefas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/


        binding.bnvMenu.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.perfil:
                    replaceFragment(new PerfilFragment());
                    break;
                case R.id.FB_Add_tarefas:
                    replaceFragment(new AdicionarTarefaFragment());
                    break;
                case R.id.avioes:
                    replaceFragment(new ListaAvioesFragment());
                    break;
                case R.id.btnListaTarefas:
                    replaceFragment(new ListaTarefasFragment());
                    break;
                case R.id.btnAdicionarTarefa:
                    replaceFragment(new ListaTarefasFragment());
                    break;
                    /*
                    case R.id.:
                    replaceFragment(new DetalhesAviaoFragment());
                    break;
                    case R.id.:
                    replaceFragment(new DetalhesTarefaFragment());
                    break;

                     */
            }


            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fl_menu, fragment);
        fragmentTransaction.commit();

    }

    /*SPINNER
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}