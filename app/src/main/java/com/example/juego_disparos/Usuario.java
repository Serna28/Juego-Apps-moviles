package com.example.juego_disparos;

public class Usuario {

    String Nombre, Uid,email,Imagen;
    int Zombie;

    public Usuario(String nombre, String uid, String email,String imagen, int zombie) {
        Nombre = nombre;
        Uid = uid;
        this.email = email;
        Zombie = zombie;
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getUid() {
        return Uid;
    }

    public String getImagen(){return Imagen;}

    public String getEmail() {
        return email;
    }

    public int getZombie() {
        return Zombie;
    }

    public Usuario()
    {


    }
}
