package com.example.juego_disparos;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Escenario extends AppCompatActivity {

    String NOMBRES,UIDS,ZOMBIES;
    TextView TvContador,TvNombre,TvTiempo;
    ImageView IvZombie;
    int contador =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario);

        TvContador = findViewById(R.id.TvContador);
        TvNombre = findViewById(R.id.TvNombre);
        TvTiempo = findViewById(R.id.TvTiempo);

        IvZombie = findViewById(R.id.IvZombie);

        Bundle intent  = getIntent().getExtras();

        UIDS = intent.getString("UID");
        NOMBRES = intent.getString("NOMBRE");
        ZOMBIES = intent.getString("ZOMBIE");

        TvNombre.setText(NOMBRES);
        TvContador.setText(ZOMBIES);

        //Al Clickear la iamgen
        IvZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cambia la imagen del zombie normal al zombie mongolo

                contador++;
                TvContador.setText(String.valueOf(contador));

                IvZombie.setImageResource(R.drawable.zomiegolpeado);

                //Volver a la iamgen normal del zombie

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IvZombie.setImageResource(R.drawable.zombienormal);

                    }
                },500);

            }
        });
    }
}