package com.example.juego_disparos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference Jugadores;



    TextView Mipuntunaciontxt,uid,correo,nombre,Menutxt,Zombies;

    Button BtnJugar,BtnPuntuaciones,BtnAcercaDe,BtnCerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        Jugadores = firebaseDatabase.getReference("BaseDatos Jugadores");

        Mipuntunaciontxt = findViewById(R.id.Mipuntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        Zombies = findViewById(R.id.Zombies);
        Menutxt = findViewById(R.id.Menutxt);


        BtnJugar = findViewById(R.id.BtnJugar);
        BtnPuntuaciones = findViewById(R.id.BtnPuntuaciones);
        BtnAcercaDe = findViewById(R.id.BtnAcercaDe);
        BtnCerrar = findViewById(R.id.BtnCerrar);

        BtnCerrar.setOnClickListener(view -> CerrarSesion());


        BtnJugar.setOnClickListener(view -> {
            Toast.makeText(Menu.this, "Jugar", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Menu.this, Escenario.class);

            String UidS = uid.getText().toString();
            String NombreS = nombre.getText().toString();
            String ZombiesS = Zombies.getText().toString();

            intent.putExtra("UID",UidS);
            intent.putExtra("NOMBRE",NombreS);
            intent.putExtra("ZOMBIE",ZombiesS);

            startActivity(intent);
            Toast.makeText(Menu.this,"Enviando",Toast.LENGTH_SHORT).show();


        });

        BtnPuntuaciones.setOnClickListener(view -> Toast.makeText(Menu.this,"Puntuaciones",Toast.LENGTH_SHORT).show());

        BtnAcercaDe.setOnClickListener(view -> Toast.makeText(Menu.this,"Acerca de nosotros",Toast.LENGTH_SHORT).show());


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
            Consulta();
            Toast.makeText(this, "Online",Toast.LENGTH_SHORT).show();

        }
        else
        {
            startActivity(new Intent(Menu.this,MainActivity.class));
            finish();
        }
    }

    //Cerrar Sesión
    private void CerrarSesion()
    {
        auth.signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
    }

    private void Consulta()
    {

        //Consulta
        Query query = Jugadores.orderByChild("email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot Db : dataSnapshot.getChildren())
                {
                    String Zombiestring = "" + Db.child("Zombie").getValue();
                    String uidstring = "" + Db.child("Uid").getValue();
                    String emailstring = "" + Db.child("email").getValue();
                    String nombrestring = "" + Db.child("Nombre").getValue();

                    Zombies.setText(Zombiestring);
                    uid.setText(uidstring);
                    correo.setText(emailstring);
                    nombre.setText(nombrestring);




                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}