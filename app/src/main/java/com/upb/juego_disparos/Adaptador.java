package com.upb.juego_disparos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Myholder> {


    private Context context;
    private List <Usuario> usuarioList;

    //Constructor
    public Adaptador(Context context, List<Usuario> usuarioList)
    {
        this.context = context;
        this.usuarioList = usuarioList;
    }



    @NonNull
    @Override

    //Inflamos Diseño
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.jugadores,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        //Obtiene modelo


        String Nombre = usuarioList.get(position).getNombre();
        String email = usuarioList.get(position).getEmail();
        int Zombie = usuarioList.get(position).getZombie();

        //Conversion

        String Z = String.valueOf(Zombie);

        //Datos

        holder.NombreJugador.setText(Nombre);
        holder.CorreoJugador.setText(email);
        holder.PuntajeJugador.setText(Z);

        //Imagen Jugador







    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }


    //Prosigue aqui

    public class Myholder extends RecyclerView.ViewHolder
    {

        CircleImageView ImagenJugador;
        TextView NombreJugador,CorreoJugador,PuntajeJugador;




        public  Myholder(@NonNull View itemView)
        {

            //Inicializa
            super(itemView);


            NombreJugador = itemView.findViewById(R.id.NombreJugador);
            CorreoJugador = itemView.findViewById(R.id.CorreoJugador);
            PuntajeJugador = itemView.findViewById(R.id.PuntajeJugador);


        }
    }
}
