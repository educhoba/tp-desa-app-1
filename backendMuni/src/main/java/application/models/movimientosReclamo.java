package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="movimientosReclamo")
public class movimientosReclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimientosReclamo")
    private Integer idMovimiento;

    public movimientosReclamo(){

    }

    public movimientosReclamo(Integer id){
        this.idMovimiento = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idMovimiento;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
