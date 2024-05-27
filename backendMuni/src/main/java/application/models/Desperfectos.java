package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="desperfectos")
public class Desperfectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDesperfectos")
    private Integer idDesperfectos;

    public Desperfectos(){

    }

    public Desperfectos(String pk, Integer id){
        this.idDesperfectos = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idDesperfectos;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
