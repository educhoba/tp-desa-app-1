package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Comercios")
public class Comercios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComercios")
    private Integer idComercios;

    public Comercios(){

    }

    public Comercios(Integer id){
        this.idComercios = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idComercios;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
