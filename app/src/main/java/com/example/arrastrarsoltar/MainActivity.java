package com.example.arrastrarsoltar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear objeto -tarea- de la clase TimerTask
        //Crear un hilo para poder ejecutar un Activity y cerrar la actual
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent;
                //Evalua si existen datos de un usuario previamente registrado
                if(nuevoUsuario()){
                    //En caso de existir datos almacenados se ejecuta la activity del menu principal
                    intent = new Intent(MainActivity.this, MenuActivity.class);
                }else{
                    //En caso de no exisrie satos almacenados, se ejecuta la activity de Login
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                //Se ejecuta la activity correspondimiento
                startActivity(intent);
                //Se elimina la Activity de presentación
                finish();
            }//run
        };//tarea
        //Creacion de un objeto tiempo de la clase Timer
        Timer tiempo = new Timer();
        //Se define dos segundos en ejecutar la activity Menu o Login
        tiempo.schedule(tarea,2000);

    }//onCreate

    public boolean nuevoUsuario(){
        //Se define el acceso al archivo donde se guardan las preferencias
        SharedPreferences sharedPreferences = getSharedPreferences("user.dat",MODE_PRIVATE);
        //Retorna el estatus de registrado si existe sino regresa falso
        return sharedPreferences.getBoolean("registro", false);
    }//nuevo usuario

}