package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Rubros")
public class Rubros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRubro")
    private Integer idRubro;

    public Rubros(){

    }

    public Rubros(String pk, Integer id){
        this.idRubro = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idRubro;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
