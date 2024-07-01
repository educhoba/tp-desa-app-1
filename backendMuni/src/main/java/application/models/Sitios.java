package application.models;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name="Sitios")
public class Sitios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSitio")
    private Integer idSitio;
    @Column(name = "latitud",nullable = true)
    private Float latitud;
    @Column(name = "longitud",nullable = true)
    private Float longitud;
    @Column(name = "calle", length = 150, nullable = true)
    private String calle;
    @Column(name = "numero",nullable = true)
    private Integer numero;
    @Column(name = "entreCalleA", length = 150, nullable = true)
    private String entreCalleA;
    @Column(name = "entreCalleB", length = 150, nullable = true)
    private String entreCalleB;
    @Column(name = "descripcion", length = 300, nullable = true)
    private String descripcion;
    @Column(name = "aCargoDe", length = 200, nullable = true)
    private String aCargoDe;
    @Column(name = "apertura", nullable = true)
    private Time apertura;
    @Column(name = "cierre", nullable = true)
    private Time cierre;
    @Column(name = "comentarios", nullable = true)
    private String comentarios;


    public Sitios(){

    }

    public Sitios(String pk, Integer id){
        this.idSitio = id;
    }

    //<editor-fold desc="Getters">
    public Integer getIdSitio(){
        return this.idSitio;
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

    public Time getApertura() {
        return apertura;
    }

    public void setApertura(Time apertura) {
        this.apertura = apertura;
    }

    public Time getCierre() {
        return cierre;
    }

    public void setCierre(Time cierre) {
        this.cierre = cierre;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
