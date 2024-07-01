package application.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="movimientosDenuncia")
public class movimientosDenuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento")
    private Integer idMovimiento;
    @Column(name = "idDenuncia", nullable = false)
    private Integer idDenuncia;
    @Column(name = "responsable",length = 150, nullable = false)
    private String responsable;
    @Column(name = "causa",length = 1000, nullable = false)
    private String causa;
    @Column(name = "fecha", nullable = true)
    private Date fecha;

    public movimientosDenuncia(){

    }

    public movimientosDenuncia(Integer id){
        this.idMovimiento = id;
    }





    //<editor-fold desc="Getters">
    public Integer getIdMovimiento() {
        return idMovimiento;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
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

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    //</editor-fold>


}
