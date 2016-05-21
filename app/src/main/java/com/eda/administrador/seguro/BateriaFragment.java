package com.eda.administrador.seguro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriaFragment extends Fragment {

    private static final String ARG_CODIGO_USUARIO = "0000";

    private String CodigoUsuario = "0000";


    public static BateriaFragment newInstance(String Usuario) {
        BateriaFragment fragment = new BateriaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODIGO_USUARIO, Usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            CodigoUsuario = getArguments().getString(ARG_CODIGO_USUARIO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bateria, container, false);
    }


}
