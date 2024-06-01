package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Vecinos")
public class Vecinos {
    @Id
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;
    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 150, nullable = false)
    private String apellido;
    @Column(name = "direccion", length = 250, nullable = true)
    private String direccion;
    @Column(name = "codigoBarrio", nullable = true)
    private int codigoBarrio;

    public Vecinos(){

    }

    public Vecinos(String pk){
        this.documento = pk;
    }

    //<editor-fold desc="Getters">
    public String getDocumento(){
        return this.documento;
    }public String getNombre(){
        return this.nombre;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setPk(String pk){
        this.documento = pk;
    }
    //</editor-fold>


}
