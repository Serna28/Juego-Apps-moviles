package com.example.juego_disparos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText CorreoElectronico,Contrasena,Nombre;
    Button Registrar;
    FirebaseAuth auth;
    int contador=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        //Conexion

        CorreoElectronico= findViewById(R.id.CorreoElectronico);
        Contrasena= findViewById(R.id.Contrasena);
        Registrar = findViewById(R.id.Registrar);
        Nombre = findViewById(R.id.Nombre);

        auth = FirebaseAuth.getInstance();

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = CorreoElectronico.getText().toString();
                String password = Contrasena.getText().toString();

                //Validación

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    CorreoElectronico.setError("Correo Valido");
                    CorreoElectronico.setFocusable(true);
                }
                else
                {
                    RegistrarJugador(email,password);
                }
            }
        });


    }

    //Metodo Registro Jugador

    private void RegistrarJugador(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser User = auth.getCurrentUser();

                    assert User !=null;
                    String uidString = User.getUid();
                    String CorreoString = CorreoElectronico.getText().toString();
                    String PassString = Contrasena.getText().toString();
                    String NomString = Nombre.getText().toString();

                    HashMap<Object,Object>DatosJugador = new HashMap<>();

                    DatosJugador.put("Uid",uidString);
                    DatosJugador.put("email",uidString);
                    DatosJugador.put("password",uidString);
                    DatosJugador.put("Nombre",uidString);
                    DatosJugador.put("Zombie",contador);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("BaseDatos Jugadores");
                    reference.child(uidString).setValue(DatosJugador);
                    Toast.makeText(Registro.this, "Jugador Registrado Exitosamente", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6)
                {
                    Contrasena.setError("Debe ser mayor a 6");
                    Contrasena.setFocusable(true);
                }

            }
            //Sí falla, muestra esto
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registro.this, "Error al registrar"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}