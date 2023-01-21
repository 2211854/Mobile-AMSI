package pt.ipleiria.estg.dei.mjrammobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Perfil;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;

public class PerfilFragment extends Fragment {

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
        perfil = Singleton.getInstance(getContext()).getPerfilBD();
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

        tvEmail.setText(perfil.getEmail());
        tvNib.setText(perfil.getNib());
        tvTelemovel.setText(perfil.getTelemovel());
        tvDataregisto.setText(perfil.getDataregisto());


        return v;
    }
}