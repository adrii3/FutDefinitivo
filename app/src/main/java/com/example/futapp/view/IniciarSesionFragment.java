package com.example.futapp.view;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futapp.AppViewModel;
import com.example.futapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IniciarSesionFragment extends Fragment {

    private Button iniciarSesionBoton, registroBoton;
    private EditText usuarioEditText , contrasenyaEditText;

    public IniciarSesionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AppViewModel appViewModel = ViewModelProviders.of(requireActivity()).get(AppViewModel.class);
        iniciarSesionBoton = view.findViewById(R.id.boton_iniciar_Sesion);

        iniciarSesionBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionBoton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appViewModel.iniciarSesion(usuarioEditText.getText().toString(),contrasenyaEditText.getText().toString());


                    }
                });
            }
        });


        registroBoton = view.findViewById(R.id.boton_registro);
        registroBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.registroFragment);
            }
        });

        appViewModel.estadoAutenticacionMLD.observe(getViewLifecycleOwner(), new Observer<AppViewModel.EstadoAutenticacion>() {
            @Override
            public void onChanged(AppViewModel.EstadoAutenticacion estadoAutenticacion) {
                switch (estadoAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).navigate(R.id.resultadosFragment);
                        break;

                    case NO_AUTENTICADO:
                        Toast.makeText(getContext(),"Credenciales no validas", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Navigation.findNavController(view).popBackStack(R.id.resultadosFragment, false);
                    }
                });



    }
}
