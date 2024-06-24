package application.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="denuncias")
public class Denuncias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDenuncias")
    private Integer idDenuncias;
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;
    @Column(name = "idSitio", nullable = true)
    private Integer idSitio;
    @Column(name = "descripcion", length = 2000, nullable = true)
    private String descripcion;
    @Column(name = "estado", length = 150, nullable = true)
    private String estado;
    @Column(name = "aceptaResponsabilidad", nullable = false)
    private Integer aceptaResponsabilidad;
    @OneToMany
    @JoinColumn(name = "idDenuncia", referencedColumnName = "idDenuncias", insertable = false, updatable = false)
    private List<Imagenes> imagenes;

    public Denuncias(){

    }

    public Denuncias(Integer id){
        this.idDenuncias = id;
    }

    //<editor-fold desc="Getters">

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAceptaResponsabilidad() {
        return aceptaResponsabilidad;
    }

    public void setAceptaResponsabilidad(Integer aceptaResponsabilidad) {
        this.aceptaResponsabilidad = aceptaResponsabilidad;
    }

    public Integer getIdDenuncias() {
        return idDenuncias;
    }

    public void setIdDenuncias(Integer idDenuncias) {
        this.idDenuncias = idDenuncias;
    }

    public List<Imagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagenes> imagenes) {
        this.imagenes = imagenes;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
