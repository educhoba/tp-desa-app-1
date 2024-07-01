package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Rubros")
public class Rubros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRubro")
    private Integer idRubro;
    @Column(name = "descripcion",length = 200, nullable = false)
    private String descripcion;

    public Rubros(){

    }

    public Rubros(String pk, Integer id){
        this.idRubro = id;
    }

    //<editor-fold desc="Getters">
    public Integer getIdRubro(){
        return this.idRubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
