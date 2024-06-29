package com.example.myapplication.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reclamo {
    @SerializedName("idReclamo")
    private Integer idReclamo;
    @SerializedName("documento")
    private String documento;
    @SerializedName("legajo")
    private Integer legajo;
    @SerializedName("idSitio")
    private Integer idSitio;
    @SerializedName("idDesperfecto")
    private Integer idDesperfecto;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("estado")
    private String estado;
    @SerializedName("idReclamoUnificado")
    private String idReclamoUnificado;
    @SerializedName("imagenes")
    private List<Imagenes> imagenes; // Asegúrate de tener la clase Imagen definida correctamente si es un tipo complejo
    @SerializedName("movimientos")
    private List<MovimientoReclamo> movimientos; // Asegúrate de tener la clase Movimiento definida correctamente si es un tipo complejo

    private SitiosManuales sitioManual;
    public Reclamo() {
        // Constructor vacío requerido por Retrofit
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdReclamoUnificado() {
        return idReclamoUnificado;
    }

    public void setIdReclamoUnificado(String idReclamoUnificado) {
        this.idReclamoUnificado = idReclamoUnificado;
    }

    public List<Imagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagenes> imagenes) {
        this.imagenes = imagenes;
    }

    public List<MovimientoReclamo> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReclamo> movimientos) {
        this.movimientos = movimientos;
    }

    public SitiosManuales getSitioManual() {
        return sitioManual;
    }

    public void setSitioManual(SitiosManuales sitioManual) {
        this.sitioManual = sitioManual;
    }

}
