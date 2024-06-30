package com.example.myapplication.models;

public class Denuncias {
    private int idDenuncias;

    private String documento;
    private Integer idSitio;
    private String descripcion;
    private String estado;
    private Integer aceptaResponsabilidad;
    private String denunciado;

    public Denuncias() {
    }

    // Getters y Setters

    public int getIdDenuncias() {
        return idDenuncias;
    }

    public void setIdDenuncias(int idDenuncias) {
        this.idDenuncias = idDenuncias;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAceptaResponsabilidad() {
        return aceptaResponsabilidad;
    }

    public void setAceptaResponsabilidad(Integer aceptaResponsabilidad) {
        this.aceptaResponsabilidad = aceptaResponsabilidad;
    }

    public String getDenunciado() {
        return denunciado;
    }

    public void setDenunciado(String denunciado) {
        this.denunciado = denunciado;
    }
}
