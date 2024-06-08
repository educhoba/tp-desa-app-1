package com.example.myapplication;

public class Usuarios {
    private String documento;
    private String email;
    private String contrasenia;
    public Usuarios() {
        // Constructor vacío requerido para Firebase o Gson, por ejemplo
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
