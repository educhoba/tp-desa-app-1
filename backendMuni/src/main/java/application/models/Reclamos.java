package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Reclamos")
public class Reclamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reclamos")
    private Integer idReclamo;

    public Reclamos(){

    }

    public Reclamos(Integer id){
        this.idReclamo = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idReclamo;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
