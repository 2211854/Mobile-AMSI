package pt.ipleiria.estg.dei.mjrammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pt.ipleiria.estg.dei.mjrammobile.databinding.ActivityMainBinding;
import pt.ipleiria.estg.dei.mjrammobile.fragments.HomeFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.PerfilFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.TabelasFragment;
import pt.ipleiria.estg.dei.mjrammobile.vistas.DetalhesActivity;

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
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.perfil:
                    replaceFragment(new PerfilFragment());
                    break;
                case R.id.avioes:
                    replaceFragment(new TabelasFragment());
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