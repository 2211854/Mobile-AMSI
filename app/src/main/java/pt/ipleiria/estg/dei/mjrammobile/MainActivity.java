package pt.ipleiria.estg.dei.mjrammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pt.ipleiria.estg.dei.mjrammobile.databinding.ActivityMainBinding;
import pt.ipleiria.estg.dei.mjrammobile.fragments.AdicionarTarefaFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaTarefasFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.ListaVoosFragment;
import pt.ipleiria.estg.dei.mjrammobile.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
{
    private String username;
    private String token;
    public static final String SHARED_USER="DADOS_USER";
    public static final String USERNAME="USERNAME"; // NOME
    public static final String TOKEN="TOKEN";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carregarToken();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListaVoosFragment());

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
                    replaceFragment(new ListaVoosFragment());
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

    private void carregarToken() {
        username=getIntent().getStringExtra(USERNAME);
        token= getIntent().getStringExtra(TOKEN);
        SharedPreferences infoUser=getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE);

        if(username!=null && token!=null)  {
            SharedPreferences.Editor editor =infoUser.edit();
            editor.putString(USERNAME, username);
            editor.putString(TOKEN, token);
            editor.apply();
        }

    }


    /*public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}