package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Denuncias {
    @SerializedName("idDenuncias")
    private int idDenuncias;

    @SerializedName("documento")
    private String documento;
    @SerializedName("idSitio")
    private Integer idSitio;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("estado")
    private String estado;
    @SerializedName("aceptaResponsabilidad")
    private Integer aceptaResponsabilidad;
    @SerializedName("denunciado")
    private String denunciado;
    @SerializedName("imagenes")
    private List<Imagenes> imagenes; // Asegúrate de tener la clase Imagen definida correctamente si es un tipo complejo
    @SerializedName("movimientos")
    private List<MovimientoDenuncia> movimientos; // Asegúrate de tener la clase Movimiento definida correctamente si es un tipo complejo

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

    public List<Imagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagenes> imagenes) {
        this.imagenes = imagenes;
    }

    public List<MovimientoDenuncia> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDenuncia> movimientos) {
        this.movimientos = movimientos;
    }
}
