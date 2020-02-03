package com.example.futapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.futapp.model.Resultado;
import com.example.futapp.model.Usuario;

import java.util.List;

@Dao
public abstract class AppDAO {

    @Insert
    public abstract void insertarUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE usuario = :usuario AND contrasenya = :contrasenya")
    public abstract Usuario autentificar(String usuario, String contrasenya);

    @Query("SELECT * FROM Usuario WHERE usuario= :usuario")
    public abstract Usuario comprobarUsuarioDisponible(String usuario);

    @Insert
    public abstract void insertarResultado(Resultado resultado);

    @Query("SELECT * FROM Resultado")
    public abstract LiveData<List<Resultado>> obtenerResultados();
}
