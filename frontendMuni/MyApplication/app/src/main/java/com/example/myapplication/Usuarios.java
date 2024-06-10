package com.example.myapplication;


import java.io.Serializable;


public class Usuarios implements Serializable {
    private String documento;
    private String email;
    private String contrasenia;
    public Usuarios() {
    }
    public Usuarios(String documento, String email, String contrasenia) {
        this.documento = documento;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    // Getters y setters
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }



}
