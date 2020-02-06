package com.example.futapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.futapp.db.AppDAO;
import com.example.futapp.db.AppDataBase;
import com.example.futapp.model.Resultado;
import com.example.futapp.model.Usuario;

import java.util.List;


public class AppViewModel extends AndroidViewModel {


    public enum EstadoAutenticacion {
        AUTENTICADO,
        NO_AUTENTICADO,
        AUTENTICACION_INVALIDA
    }
    public enum EstadoRegistro {
        REGISTRO_COMPLETO,
        NOMBRE_NO_DISPONIBLE,
        INICIO_REGISTRO
    }

    private AppDAO appDAO;
    public Usuario usuarioLogeado;


    public MutableLiveData<EstadoAutenticacion> estadoAutenticacionMLD = new MutableLiveData<>();
    public MutableLiveData<EstadoRegistro> estadoRegistroMLD = new MutableLiveData<>();



    public AppViewModel(@NonNull Application application) {
        super(application);
        appDAO = AppDataBase.getInstance(application).dao();

        estadoAutenticacionMLD.setValue(EstadoAutenticacion.NO_AUTENTICADO);
        estadoRegistroMLD.setValue(EstadoRegistro.INICIO_REGISTRO);
    }

    public void iniciarRegistro(){
        estadoRegistroMLD.postValue(EstadoRegistro.INICIO_REGISTRO);
    }

    public void crearCuentaEIniciarSesion(final String nombre, final String usuario, final String email, final String contrasenya){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario1 = appDAO.comprobarUsuarioDisponible(usuario);
                if(usuario1==null){
                    appDAO.insertarUsuario(new Usuario(usuario, contrasenya));
                    estadoRegistroMLD.postValue(EstadoRegistro.REGISTRO_COMPLETO);
                    iniciarSesion(usuario, contrasenya);
                }else{
                    estadoRegistroMLD.postValue(EstadoRegistro.NOMBRE_NO_DISPONIBLE);
                }
            }
        });
    }

    public void iniciarSesion(final String usuario, final String contrasenya){
        Log.e("ABCD", "inciando asynctask");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.e("ABCD", "comprobando suaurio");
                Log.e("ABCD", "");
                Usuario usuario1 = appDAO.comprobarUsuarioDisponible(usuario);
                Log.e("ABCD", "usuario comprobado");
                if(usuario1!=null){
                    Log.e("ABCD", "usuario es distinto de null");
                    usuarioLogeado = usuario1;
                    estadoAutenticacionMLD.postValue(EstadoAutenticacion.AUTENTICADO);
                }else{
                    Log.e("ABCD", "usuario es null");
                    estadoAutenticacionMLD.postValue(EstadoAutenticacion.AUTENTICACION_INVALIDA);
                }
            }
        });
    }

    public void cerrarSesion(){
        usuarioLogeado= null;
        estadoAutenticacionMLD.setValue(EstadoAutenticacion.NO_AUTENTICADO);
    }

    public LiveData<List<Resultado>> obtenerResultados(){
        return appDAO.obtenerResultados();
    }
}