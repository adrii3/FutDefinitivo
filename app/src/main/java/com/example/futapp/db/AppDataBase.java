package com.example.futapp.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.futapp.model.Resultado;
import com.example.futapp.model.Usuario;

@Database(entities = {Usuario.class, Resultado.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    public abstract AppDAO dao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (AppDataBase.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "app-db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    insertarDatosIniciales();
                                }
                            })
                            .build();
                }

            }
        }
        return INSTANCE;
    }

    private static void insertarDatosIniciales() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                INSTANCE.dao().insertarResultado(new Resultado("Barcelona","Madrid",3,4));
                INSTANCE.dao().insertarResultado(new Resultado("Barcelona","Valencia",3,1));
                INSTANCE.dao().insertarResultado(new Resultado("Betis","Madrid",5,4));
            }
        });
    }
}
