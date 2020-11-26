package com.example.lab13;

public class Contacto {
    private String id,idUsuario,nombreContacto,numeroContacto;

    public Contacto(){

    }
    public Contacto(String id,String idUsuario,String nombreContacto,String numeroContacto){
        this.id=id;
        this.idUsuario=idUsuario;
        this.nombreContacto=nombreContacto;
        this.numeroContacto=numeroContacto;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }
}
