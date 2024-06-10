package com.example.myapplication;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.io.Serializable;

public class Inspector implements Serializable {
    @SerializedName("legajo")
    private Integer legajo;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("documento")
    private String documento;

    @SerializedName("password")
    private String password;

    @SerializedName("sector")
    private String sector;

    @SerializedName("codigoBarrio")
    private int codigoBarrio;

    @SerializedName("fechaIngreso")
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

   /* public Date getFechaIngreso() {
        return fechaIngreso;
    }*/

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
