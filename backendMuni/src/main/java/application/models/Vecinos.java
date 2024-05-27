package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Vecinos")
public class Vecinos {
    @Id
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;

    public Vecinos(){

    }

    public Vecinos(String pk){
        this.documento = pk;
    }

    //<editor-fold desc="Getters">
    public String getPk(){
        return this.documento;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setPk(String pk){
        this.documento = pk;
    }
    //</editor-fold>


}
