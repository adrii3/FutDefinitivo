package com.example.futapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String usuario;
    public String email;
    public String contrasenya;

    public Usuario(String usuario, String contrasenya){
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.contrasenya = contrasenya;
    }
}
