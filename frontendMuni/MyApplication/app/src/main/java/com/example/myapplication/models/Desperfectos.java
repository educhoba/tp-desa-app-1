package com.example.myapplication.models;

public class Desperfectos {
    private Integer idDesperfecto;
    private String descripcion;
    private Integer idRubro;

    // Constructors
    public Desperfectos() {
        // Default constructor
    }

    public Desperfectos(String pk, Integer id) {
        this.idDesperfecto = id;
    }

    // Getters and Setters
    public Integer getIdDesperfecto() {
        return idDesperfecto;
    }

    public void setIdDesperfecto(Integer idDesperfecto) {
        this.idDesperfecto = idDesperfecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }
}
