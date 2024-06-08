package com.example.myapplication;

import java.util.Date;
public class Inspector {
    private Integer legajo;
    private String nombre;
    private String apellido;
    private String documento;
    private String password;
    private String sector;
    private int codigoBarrio;
    private Date fechaIngreso;


    public Inspector(Integer legajo, String nombre, String apellido, String documento, String password, String sector, int codigoBarrio, Date fechaIngreso) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.password = password;
        this.sector = sector;
        this.codigoBarrio = codigoBarrio;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public String getDocumento() {
        return documento;
    }

    public String getPassword() {
        return password;
    }

    public String getSector() {
        return sector;
    }

    public int getCodigoBarrio() {
        return codigoBarrio;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    // Setters
    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setCodigoBarrio(int codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}
