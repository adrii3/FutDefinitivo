package com.example.futapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.futapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BienvenidaFragment extends Fragment {


    Button botonIniciarSesion;
    Button botonRegistrarse;

    public BienvenidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bienvenida, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        botonIniciarSesion = view.findViewById(R.id.boton_bienvenida_iniciar_sesion);
        botonRegistrarse = view.findViewById(R.id.boton_bienvenida_registro);


        botonIniciarSesion.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.iniciarSesionFragment);
            }
        });


        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.registroFragment);
            }
        });

    }
}
