package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legajo")
    private Integer legajo;

    public Personal(){

    }

    public Personal(String pk, Integer id){
        this.legajo = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.legajo;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
