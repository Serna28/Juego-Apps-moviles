package com.example.juego_disparos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Random;

public class Escenario extends AppCompatActivity {

    String NOMBRES, UIDS, ZOMBIES;
    TextView TvContador, TvNombre, TvTiempo;
    ImageView IvZombie;

    TextView TvAncho, TvAlto;

    int contador = 0;

    int Ancho, Alto;

    boolean GameOver = false;
    Dialog miDialog;

    Random aleatorio;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario);

        TvContador = findViewById(R.id.TvContador);
        TvNombre = findViewById(R.id.TvNombre);
        TvTiempo = findViewById(R.id.TvTiempo);

        miDialog = new Dialog(Escenario.this);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference("BaseDatos Jugadores");


        IvZombie = findViewById(R.id.IvZombie);

        Bundle intent = getIntent().getExtras();

        UIDS = intent.getString("UID");
        NOMBRES = intent.getString("NOMBRE");
        ZOMBIES = intent.getString("ZOMBIE");

        TvNombre.setText(NOMBRES);
        TvContador.setText(ZOMBIES);


        TvAlto = findViewById(R.id.TvAlto);
        TvAncho = findViewById(R.id.TvAncho);

        pantalla();
        TimeOut();

        //Al Clickear la iamgen
        IvZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cambia la imagen del zombie normal al zombie mongolo

                if (!GameOver) {

                    contador++;
                    TvContador.setText(String.valueOf(contador));

                    IvZombie.setImageResource(R.drawable.zomiegolpeado);

                    //Volver a la iamgen normal del zombie

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            IvZombie.setImageResource(R.drawable.zombienormal);
                            Movimiento();


                        }
                    }, 500);

                }
            }
        });

    }

    //TAMAÑO pANTALLA
    private void pantalla() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        Ancho = point.x;
        Alto = point.y;

        String ANCHOS = String.valueOf(Ancho);
        String ALTOS = String.valueOf(Alto);

        aleatorio = new Random();


    }

    //Mover el zombie en posiciones Random

    private void Movimiento() {
        int minimo = 0;

        int MaxX = Ancho - IvZombie.getWidth();
        int MaxY = Alto - IvZombie.getHeight();

        int randomX = aleatorio.nextInt(((MaxX - minimo) + 1) + minimo);
        int randomY = aleatorio.nextInt(((MaxY - minimo) + 1) + minimo);


        IvZombie.setX(randomX);
        IvZombie.setY(randomY);
    }

    //Time Out

    private void TimeOut() {
        //Sacado de https://developer.android.com/reference/android/os/CountDownTimer#java

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                //añadido
                long segundosRestantes = millisUntilFinished / 1000;
                TvTiempo.setText(segundosRestantes + "S");
            }

            //Time Out

            public void onFinish() {
                TvTiempo.setText("0s");
                GameOver = true;
                MensajeGameOver();
                GuardarResultado( "Zombie",contador);
            }
        }.start();

    }

    //Gameover Screen
    private void MensajeGameOver() {


        TextView SeAcabotxt, Hasmatadotxt, Numerotxt;
        Button Reintentar, IrMenu, Puntajes;

        miDialog.setContentView(R.layout.activity_gameover);

        SeAcabotxt = miDialog.findViewById(R.id.SeAcabotxt);
        Hasmatadotxt = miDialog.findViewById(R.id.Hasmatadotxt);
        Numerotxt = miDialog.findViewById(R.id.Numerotxt);

        Reintentar = miDialog.findViewById(R.id.Reintentar);
        IrMenu = miDialog.findViewById(R.id.IrMenu);
        Puntajes = miDialog.findViewById(R.id.Puntajes);

        String zombies = String.valueOf(contador);
        Numerotxt.setText(zombies);

        Reintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contador =0;

                miDialog.dismiss();
                TvContador.setText("0");
                GameOver= false;
                TimeOut();
                Movimiento();

            }
        });

        IrMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Escenario.this,Menu.class));

            }
        });

        Puntajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        miDialog.show();


    }

    //Guardar Resultados

    private void GuardarResultado(String key, int zombies)
    {
        HashMap <String,Object> hashMap = new HashMap<>();
        hashMap.put(key,zombies);

        JUGADORES.child(user.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Escenario.this, "Puntaje Actulizado", Toast.LENGTH_SHORT).show();

            }
        });


    }
}