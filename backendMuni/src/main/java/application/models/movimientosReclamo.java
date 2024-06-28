package application.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="movimientosReclamo")
public class movimientosReclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento", nullable = false)
    private Integer idMovimiento;
    @Column(name = "idReclamo", nullable = false)
    private Integer idReclamo;
    @Column(name = "responsable",length = 150, nullable = false)
    private String responsable;
    @Column(name = "causa",length = 1000, nullable = false)
    private String causa;
    @Column(name = "fecha", nullable = true)
    private Date fecha;


    public movimientosReclamo(){

    }

    public movimientosReclamo(Integer id){
        this.idMovimiento = id;
    }

    //<editor-fold desc="Getters">

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

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
