package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Profesionales")
public class Profesionales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProfesional")
    private Integer idProfesional;

    public Profesionales(){

    }

    public Profesionales(Integer id){
        this.idProfesional = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idProfesional;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
