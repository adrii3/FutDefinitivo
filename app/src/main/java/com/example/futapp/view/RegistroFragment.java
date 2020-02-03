package com.example.futapp.view;


import android.os.Bundle;

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

import com.example.futapp.AppFragment;
import com.example.futapp.AppViewModel;
import com.example.futapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends AppFragment {

    private Button botonRegistrarse;

    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final AppViewModel appViewModel = ViewModelProviders.of(requireActivity()).get(AppViewModel.class);
        final EditText nombre = view.findViewById(R.id.nombre_Registro);
        final EditText usuario = view.findViewById(R.id.usuario_Registro);
        final EditText email = view.findViewById(R.id.email_Registro);
        final EditText contraseña = view.findViewById(R.id.contraseña_registro);

        Button registrarButton = view.findViewById(R.id.boton_Registrarse);


        appViewModel.inicarRegistro();


        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.crearCuentaEIniciarSesion(nombre.getText().toString(), usuario.getText().toString(), email.getText().toString(), contraseña.getText().toString());
            }
        });

        appViewModel.estadoRegistroMLD.observe(getViewLifecycleOwner(), new Observer<AppViewModel.EstadoRegistro>() {
            @Override
            public void onChanged(AppViewModel.EstadoRegistro estadoRegistro) {
                switch (estadoRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                        Toast.makeText(getContext(), "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        appViewModel.estadoAutenticacionMLD.observe(getViewLifecycleOwner(), new Observer<AppViewModel.EstadoAutenticacion>() {
            @Override
            public void onChanged(AppViewModel.EstadoAutenticacion estadoAutenticacion) {
                switch (estadoAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).navigate(R.id.resultadosFragment);
                        break;
                }
            }
        });
    }
}
