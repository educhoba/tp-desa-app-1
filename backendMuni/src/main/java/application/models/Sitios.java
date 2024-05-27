package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Sitios")
public class Sitios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSitio")
    private Integer idSitio;

    public Sitios(){

    }

    public Sitios(String pk, Integer id){
        this.idSitio = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idSitio;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
