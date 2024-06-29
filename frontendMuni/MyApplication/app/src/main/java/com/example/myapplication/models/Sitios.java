package com.example.myapplication.models;

import java.sql.Time;

public class Sitios {
    private Integer idSitio;
    private Float latitud;
    private Float longitud;
    private String calle;
    private Integer numero;
    private String entreCalleA;
    private String entreCalleB;
    private String descripcion;
    private String aCargoDe;
    private String comentarios;

    // Constructor
    public Sitios() {
        // Default constructor
    }

    // Getters and Setters
    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEntreCalleA() {
        return entreCalleA;
    }

    public void setEntreCalleA(String entreCalleA) {
        this.entreCalleA = entreCalleA;
    }

    public String getEntreCalleB() {
        return entreCalleB;
    }

    public void setEntreCalleB(String entreCalleB) {
        this.entreCalleB = entreCalleB;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getaCargoDe() {
        return aCargoDe;
    }

    public void setaCargoDe(String aCargoDe) {
        this.aCargoDe = aCargoDe;
    }


    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}

