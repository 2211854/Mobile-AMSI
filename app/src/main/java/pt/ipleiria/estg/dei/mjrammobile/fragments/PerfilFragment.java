package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.MainActivity;
import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.PerfilListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Perfil;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;
import pt.ipleiria.estg.dei.mjrammobile.vistas.LoginActivity;

public class PerfilFragment extends Fragment implements PerfilListener {

    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private View v;

    private TextView tvEmail, tvNib,tvTelemovel,tvDataregisto;
    private Button btnLogout;

    private Perfil perfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvEmail = v.findViewById(R.id.tv_email);
        tvNib = v.findViewById(R.id.tv_nib);
        tvTelemovel = v.findViewById(R.id.tv_telemovel);
        tvDataregisto = v.findViewById(R.id.tv_dataregisto);
        btnLogout = v.findViewById(R.id.btn_logout);

        Singleton.getInstance(getContext()).setPerfilListener(this);
        Singleton.getInstance(getContext()).getPerfilAPI(getContext());
        perfil = Singleton.getInstance(getContext()).getPerfilBD();



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(MainActivity.SHARED_USER, Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

    @Override
    public void onRefreshPerfil(Perfil perfil) {
        tvEmail.setText(perfil.getEmail());
        tvNib.setText(perfil.getNib());
        tvTelemovel.setText(perfil.getTelemovel());
        tvDataregisto.setText(perfil.getDataregisto());
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}