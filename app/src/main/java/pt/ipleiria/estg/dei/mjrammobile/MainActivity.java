package pt.ipleiria.estg.dei.mjrammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import pt.ipleiria.estg.dei.mjrammobile.databinding.ActivityMainBinding;
import pt.ipleiria.estg.dei.mjrammobile.fragments.DetalhesFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaTarefasFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.PerfilFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.TabelasFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.perfil:
                    replaceFragment(new PerfilFragment());
                    break;
                case R.id.avioes:
                    replaceFragment(new TabelasFragment());
                    break;
                /*case R.id.btnListaTarefas:
                    replaceFragment(new DetalhesFragment());
                    break;*/
                case R.id.btnListaTarefas:
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