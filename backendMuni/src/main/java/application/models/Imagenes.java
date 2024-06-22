package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="imagenes")
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImagen")
    private Integer idImagen;
    @Column(name = "idReclamo")
    private Integer idReclamo;
    @Column(name = "idDenuncia")
    private Integer idDenuncia;
    @Column(name = "idServicio")
    private Integer idServicio;
    @Column(name = "data")
    private String data;

    public Imagenes(){

    }

    public Imagenes(Integer id){
        this.idImagen = id;
    }




    //<editor-fold desc="Getters">
    public Integer getIdImagen() {
        return idImagen;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    //</editor-fold>


}
