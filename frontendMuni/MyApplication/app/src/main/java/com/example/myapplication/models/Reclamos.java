package com.example.myapplication.models;

import android.content.ContentValues;


import com.example.myapplication.data.DataContract;
import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//import kotlinx.coroutines.DisposeOnCompletion;

public class Reclamos {
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
    private Integer idReclamoUnificado;
    @SerializedName("imagenes")
    private List<Imagenes> imagenes; // Asegúrate de tener la clase Imagen definida correctamente si es un tipo complejo
    @SerializedName("movimientos")
    private List<MovimientoReclamo> movimientos; // Asegúrate de tener la clase Movimiento definida correctamente si es un tipo complejo
    @SerializedName("sitiosManuales")
    private List<SitiosManuales> sitiosManuales;

    public Reclamos() {
        sitiosManuales = new ArrayList<>();
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

    public Integer getIdReclamoUnificado() {
        return idReclamoUnificado;
    }

    public void setIdReclamoUnificado(Integer idReclamoUnificado) {
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

    public List<SitiosManuales> getSitiosManuales() {
        return sitiosManuales;
    }

    public void setSitiosManuales(List<SitiosManuales> sitiosManuales) {
        this.sitiosManuales = sitiosManuales;
    }
    public ContentValues toContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(DataContract.ReclamosEntry.ID,idReclamo);
        cv.put(DataContract.ReclamosEntry.DOCUMENTO, documento);
        cv.put(DataContract.ReclamosEntry.LEGAJO, legajo);
        cv.put(DataContract.ReclamosEntry.IDSITIO, idSitio);
        cv.put(DataContract.ReclamosEntry.IDDESPERFECTO, idDesperfecto);
        cv.put(DataContract.ReclamosEntry.DESCRIPCION, descripcion);
        cv.put(DataContract.ReclamosEntry.ESTADO, estado);
        cv.put(DataContract.ReclamosEntry.IDRECLAMOUNIFICADO, idReclamoUnificado);
        cv.put(DataContract.ReclamosEntry.SITIOMANUAL, sitiosManuales != null?( sitiosManuales.isEmpty()?null:sitiosManuales.get(0).getDescripcion()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_1, imagenes != null?( imagenes.isEmpty()? null:imagenes.get(0).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_2, imagenes != null?( imagenes.size() < 2? null:imagenes.get(1).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_3, imagenes != null?( imagenes.size() < 3? null:imagenes.get(2).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_4, imagenes != null?( imagenes.size() < 4? null:imagenes.get(3).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_5, imagenes != null?( imagenes.size() < 5? null:imagenes.get(4).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_6, imagenes != null?( imagenes.size() < 6? null:imagenes.get(5).getData()):null);
        cv.put(DataContract.ReclamosEntry.IMAGEN_7, imagenes != null?( imagenes.size() < 7? null:imagenes.get(6).getData()):null);
        return cv;
    }
}
