package pt.ipleiria.estg.dei.mjrammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import pt.ipleiria.estg.dei.mjrammobile.databinding.ActivityMainBinding;
import pt.ipleiria.estg.dei.mjrammobile.fragments.AdicionarTarefaFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaAvioesFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaTarefasFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListaAvioesFragment());

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
                /*case R.id.btnListaTarefas:
                    replaceFragment(new DetalhesFragment());
                    break;*/
                case R.id.btnListaTarefas:
                    replaceFragment(new ListaTarefasFragment());
                    break;
                case R.id.btnAdicionarTarefa:
                    replaceFragment(new ListaTarefasFragment());
                    break;
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

}