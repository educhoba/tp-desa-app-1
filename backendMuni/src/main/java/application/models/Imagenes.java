package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="imagenes")
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImagen")
    private Integer idImagen;

    public Imagenes(){

    }

    public Imagenes(Integer id){
        this.idImagen = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idImagen;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
