package com.upb.juego_disparos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BTNLOGIN,BTNREGISTRO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTNLOGIN = findViewById(R.id.BTNLOGIN);
        BTNREGISTRO = findViewById(R.id.BTNREGISTRO);

        BTNLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override

            //Manda al Login
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this,"Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }
        });

        BTNREGISTRO.setOnClickListener(new View.OnClickListener()
        {
            @Override

            //Manda al Registro
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registro.class);
                startActivity(intent);


            }
        });
    }
}