package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="denuncias")
public class Denuncias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDenuncias")
    private Integer idDenuncias;

    public Denuncias(){

    }

    public Denuncias(Integer id){
        this.idDenuncias = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idDenuncias;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
