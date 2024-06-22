package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="desperfectos")
public class Desperfectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDesperfecto")
    private Integer idDesperfecto;
    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;
    @Column(name = "idRubro",nullable = true)
    private Integer idRubro;

    public Desperfectos(){

    }

    public Desperfectos(String pk, Integer id){
        this.idDesperfecto = id;
    }

    //<editor-fold desc="Getters">
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdDesperfecto() {
        return idDesperfecto;
    }

    public void setIdDesperfecto(Integer idDesperfecto) {
        this.idDesperfecto = idDesperfecto;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
