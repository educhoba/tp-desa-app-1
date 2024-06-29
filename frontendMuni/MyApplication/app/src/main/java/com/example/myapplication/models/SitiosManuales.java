package com.example.myapplication.models;

public class SitiosManuales {
    private Integer idSitioManual;
    private String descripcion;

    public SitiosManuales() {
    }

    public SitiosManuales(Integer id) {
        this.idSitioManual = id;
    }

    // Getters
    public Integer getIdSitioManual() {
        return idSitioManual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setIdSitioManual(Integer idSitioManual) {
        this.idSitioManual = idSitioManual;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

