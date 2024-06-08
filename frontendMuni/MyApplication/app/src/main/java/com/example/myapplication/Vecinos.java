package com.example.myapplication;

public class Vecinos {
    private String documento;
    private String nombre;
    private String apellido;
    private String direccion;
    private int codigoBarrio;

    public Vecinos() {}

    public Vecinos(String documento, String nombre, String apellido, String direccion, int codigoBarrio) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.codigoBarrio = codigoBarrio;
    }

    // Getters
    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCodigoBarrio() {
        return codigoBarrio;
    }

    // Setters
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoBarrio(int codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

}
