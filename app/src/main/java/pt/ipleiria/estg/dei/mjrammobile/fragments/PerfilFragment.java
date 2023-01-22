package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.PerfilListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Perfil;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;

public class PerfilFragment extends Fragment implements PerfilListener {

    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private View v;

    private TextView tvEmail, tvNib,tvTelemovel,tvDataregisto;

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

        Singleton.getInstance(getContext()).setPerfilListener(this);
        Singleton.getInstance(getContext()).getPerfilAPI(getContext());
        perfil = Singleton.getInstance(getContext()).getPerfilBD();

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvEmail = v.findViewById(R.id.tv_email);
        tvNib = v.findViewById(R.id.tv_nib);
        tvTelemovel = v.findViewById(R.id.tv_telemovel);
        tvDataregisto = v.findViewById(R.id.tv_dataregisto);


        tvEmail.setText(perfil.getEmail());
        tvNib.setText(perfil.getNib());
        tvTelemovel.setText(perfil.getTelemovel());
        tvDataregisto.setText(perfil.getDataregisto());


//        Button btnLogout = (Button) v.findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                til_searchFilter = (TextInputLayout) v.findViewById(R.id.searchFilter_aviao);
//                tv_titulo = (TextView) v.findViewById(R.id.tv_titulo_lista_voos);
//                if(tv_titulo.getVisibility() == v.VISIBLE){
//                    tv_titulo.setVisibility(tv_titulo.GONE);
//                    til_searchFilter.setVisibility(til_searchFilter.VISIBLE);
//                    til_searchFilter.setEnabled(true);
//                }else{
//                    tv_titulo.setVisibility(tv_titulo.VISIBLE);
//                    til_searchFilter.setVisibility(til_searchFilter.GONE);
//                    til_searchFilter.setEnabled(false);
//                }
//            }
//        });
        return v;
    }

    @Override
    public void onRefreshPerfil(Perfil perfil) {

    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}