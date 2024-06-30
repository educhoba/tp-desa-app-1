package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MovimientoDenuncia {
    @SerializedName("idMovimiento")
    private Integer idMovimiento;
    @SerializedName("idDenuncia")
    private Integer idDenuncia;
    @SerializedName("responsable")
    private String responsable;
    @SerializedName("causa")
    private String causa;
    @SerializedName("fecha")
    private Date fecha;

    public MovimientoDenuncia() {
        // Constructor vacío requerido por Retrofit
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getidDenuncia() {
        return idDenuncia;
    }

    public void setidDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
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

