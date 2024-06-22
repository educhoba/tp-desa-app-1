package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="SitiosManuales")
public class SitiosManuales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSitioManual")
    private Integer idSitioManual;
    @Column(name = "descripcion")
    private String descripcion;

    public SitiosManuales(){

    }

    public SitiosManuales(Integer id){
        this.idSitioManual = id;
    }

    //<editor-fold desc="Getters">
    public Integer getidSitioManual(){
        return this.idSitioManual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
