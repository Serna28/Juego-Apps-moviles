package com.example.juego_disparos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    TextView Mipuntunaciontxt,uid,correo,nombre,Menutxt;

    Button BtnJugar,BtnPuntuaciones,BtnAcercaDe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        Mipuntunaciontxt = findViewById(R.id.Mipuntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        nombre = findViewById(R.id.nombre);
        Menutxt = findViewById(R.id.Menutxt);


        BtnJugar = findViewById(R.id.BtnJugar);
        BtnPuntuaciones = findViewById(R.id.BtnPuntuaciones);
        BtnAcercaDe = findViewById(R.id.BtnAcercaDe);


        BtnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this,"Jugar",Toast.LENGTH_SHORT).show();
            }
        });

        BtnPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this,"Puntuaciones",Toast.LENGTH_SHORT).show();
            }
        });

        BtnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this,"Acerca de nosotros",Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onStart()
    {
        UsuarioLoggeado();
        super.onStart();
    }

    //Compro de inicio de sesion

    private void UsuarioLoggeado()
    {
        if(user !=null)
        {
            Toast.makeText(this, "Online",Toast.LENGTH_SHORT).show();

        }
        else
        {
            startActivity(new Intent(Menu.this,MainActivity.class));
            finish();
        }
    }
}