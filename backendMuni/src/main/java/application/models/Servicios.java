package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="Servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idServicios")
    private Integer idServicios;
    @Column(name = "tipo", length = 20, nullable = false)
    private String tipo;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;
    @Column(name = "horario", length = 100, nullable = false)
    private String horario;
    @Column(name = "telefono", length = 25, nullable = false)
    private String telefono;
    @Column(name = "rubro", length = 200, nullable = false)
    private String rubro;
    @Column(name = "descripcion", length = 1000, nullable = false)
    private String descripcion;

    public Servicios(){

    }

    public Servicios(Integer id){
        this.idServicios = id;
    }

    //<editor-fold desc="Getters">
    public Integer getId(){
        return this.idServicios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
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
