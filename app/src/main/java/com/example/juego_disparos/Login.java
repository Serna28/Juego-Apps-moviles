package com.example.juego_disparos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText CorreoLogin,ContrasenaLogin;
    Button BtnLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Conexion vista

        CorreoLogin = findViewById(R.id.CorreoLogin);
        ContrasenaLogin = findViewById(R.id.ContrasenaLogin);
        BtnLogin = findViewById(R.id.BtnLogin);

        auth = FirebaseAuth.getInstance();

        //Click en Boton

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = CorreoLogin.getText().toString();
                String contrasena = ContrasenaLogin.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    CorreoLogin.setError("Correo Valido");
                    CorreoLogin.setFocusable(true);
                }
                else
                {
                    LoggeoJugador(email,contrasena);
                }

            }
        });
    }

    //Metodo de loggeo

    private void LoggeoJugador(String email, String contrasena) {

        auth.signInWithEmailAndPassword(email, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    FirebaseUser user = auth.getCurrentUser();
                    startActivity(new Intent(Login.this,Menu.class));

                    assert user !=null;

                    Toast.makeText(Login.this, "Bienvenido de nuevo " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            //Falló Loggeo

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText( Login.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}