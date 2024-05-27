package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="SitiosManuales")
public class SitiosManuales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSitioManual")
    private Integer idSitioManual;

    public SitiosManuales(){

    }

    public SitiosManuales(Integer id){
        this.idSitioManual = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idSitioManual;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
