package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Barrios")
public class Barrios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBarrio")
    private Integer idBarrio;

    @Column(name = "PKString", length = 150, nullable = false)
    private String nombre;

    public Barrios(){

    }

    public Barrios(Integer id, String nombre){
        this.nombre = nombre;
        this.idBarrio = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idBarrio;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
