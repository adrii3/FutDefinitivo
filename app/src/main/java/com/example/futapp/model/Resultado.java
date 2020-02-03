package com.example.futapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Resultado {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String equipo1;
    public String equipo2;
    public int goles1;
    public int goles2;


    public Resultado(String equipo1, String equipo2, int goles1, int goles2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
    }
}
