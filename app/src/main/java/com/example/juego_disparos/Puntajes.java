package com.example.juego_disparos;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Puntajes extends AppCompatActivity {

    LinearLayoutManager mLayoutManager;
    RecyclerView recycleviewusuarios;
    Adaptador adaptador;
    List <Usuario> usuarioList;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Puntajes");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLayoutManager = new LinearLayoutManager(this);
        firebaseAuth = FirebaseAuth.getInstance();
        recycleviewusuarios = findViewById(R.id.recycleviewusuarios);

        mLayoutManager.setReverseLayout(true); //Inicia ultimo a mayor
        mLayoutManager.setStackFromEnd(true); //Visualiza ultimo a primero ocultando errores
        recycleviewusuarios.setHasFixedSize(true);
        recycleviewusuarios.setLayoutManager(mLayoutManager);
        usuarioList = new ArrayList<>();

        ObtenerTodosLosUsuarios();


    }

    private void ObtenerTodosLosUsuarios() {
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref  = FirebaseDatabase.getInstance().getReference("BaseDatos Jugadores");
        ref.orderByChild("Zombie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usuarioList.clear();

                for(DataSnapshot ds : snapshot.getChildren())
                {
                    Usuario usuario = ds.getValue(Usuario.class);

                    //if(!usuario.getUid().equals(fUser.getUid()))
                    //// usuarioList.add(usuario);

                   // }



                    usuarioList.add(usuario);

                    adaptador = new Adaptador(Puntajes.this,usuarioList);

                    recycleviewusuarios.setAdapter(adaptador);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();
    }
}