package application.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legajo")
    private Integer legajo;
    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 150, nullable = false)
    private String apellido;
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;
    @Column(name = "password", length = 40, nullable = false)
    private String password;
    @Column(name = "sector", length = 200, nullable = false)
    private String sector;
    @Column(name = "categoria", nullable = true)
    private int codigoBarrio;
    @Column(name = "fechaIngreso", nullable = true)
    private Date fechaIngreso;


    public Personal(){

    }
    public Personal(String pk, Integer id, String nombre, String apellido, String documento, String password, String sector, int codigoBarrio, Date fechaIngreso){
        this.legajo = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.password = password;
        this.sector = sector;
        this.codigoBarrio = codigoBarrio;
        this.fechaIngreso = fechaIngreso;
    }

    //<editor-fold desc="Getters">
    public Integer getLegajo(){
        return this.legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public String getPassword() {
        return password;
    }

    public String getSector() {
        return sector;
    }

    public int getCodigoBarrio() {
        return codigoBarrio;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">

    public void setPassword(String pass){
        this.password = pass;
    }
    //</editor-fold>


}
