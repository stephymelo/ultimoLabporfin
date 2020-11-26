package com.example.lab13;

public class Usuario {
    private String nombre,id,correo,password;

    public Usuario(){

    }
    public Usuario(String id,String nombre,String correo,String password){
        this.nombre=nombre;
        this.id=id;
        this.correo=correo;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
