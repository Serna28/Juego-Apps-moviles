package com.upb.juego_disparos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       //New handler permit ejecutar codigo en tiempo preterminado
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Este codigo se ejecutara en una cantidad de segundos que definiremos
                Intent intent = new Intent(Splash.this,Menu.class);
                startActivity(intent);
            }
            //Osea 1.5 segundos
        }, 1500);
    }
}