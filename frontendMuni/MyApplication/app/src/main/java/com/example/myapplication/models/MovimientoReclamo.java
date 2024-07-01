package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MovimientoReclamo {
    @SerializedName("idMovimiento")
    private Integer idMovimiento;
    @SerializedName("idReclamo")
    private Integer idReclamo;
    @SerializedName("responsable")
    private String responsable;
    @SerializedName("causa")
    private String causa;
    @SerializedName("fecha")
    private Date fecha;

    public MovimientoReclamo() {
        // Constructor vacío requerido por Retrofit
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

