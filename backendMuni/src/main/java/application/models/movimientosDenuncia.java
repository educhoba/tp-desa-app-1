package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="movimientosDenuncia")
public class movimientosDenuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento")
    private Integer idMovimiento;

    public movimientosDenuncia(){

    }

    public movimientosDenuncia(Integer id){
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
