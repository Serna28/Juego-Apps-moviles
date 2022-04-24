package com.example.juego_disparos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
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