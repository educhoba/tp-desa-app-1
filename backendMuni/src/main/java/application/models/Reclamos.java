package application.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Reclamos")
public class Reclamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReclamo")
    private Integer idReclamo;
    @Column(name = "documento", length = 20, nullable = true)
    private String documento;
    @Column(name = "legajo", nullable = true)
    private Integer legajo;

    @Column(name = "idSitio", nullable = false)
    private Integer idSitio;
    @Column(name = "idDesperfecto", nullable = true)
    private Integer idDesperfecto;
    @Column(name = "descripcion", length = 1000, nullable = true)
    private String descripcion;
    @Column(name = "estado", length = 30, nullable = true)
    private String estado;
    @Column(name = "idReclamoUnificado", nullable = true)
    private Integer idReclamoUnificado;
    @OneToMany
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo", insertable = false, updatable = false)
    private List<Imagenes> imagenes;
    @OneToMany
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo", insertable = false, updatable = false)
    private List<movimientosReclamo> movimientos;

    @OneToMany
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo", insertable = false, updatable = false)
    private List<SitiosManuales> sitiosManuales;

    //ejemplo si trae 1 objeto sitio
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "idSitio", referencedColumnName = "idSitio")
    //private Sitios sitio;

    public Reclamos(){

    }

    public Reclamos(Integer id){
        this.idReclamo = id;
    }

    //<editor-fold desc="Getters">
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public Integer getIdDesperfecto() {
        return idDesperfecto;
    }

    public void setIdDesperfecto(Integer idDesperfecto) {
        this.idDesperfecto = idDesperfecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdReclamoUnificado() {
        return idReclamoUnificado;
    }

    public void setIdReclamoUnificado(Integer idReclamoUnificado) {
        this.idReclamoUnificado = idReclamoUnificado;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Imagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagenes> imagenes) {
        this.imagenes = imagenes;
    }

    public List<movimientosReclamo> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<movimientosReclamo> movimientos) {
        this.movimientos = movimientos;
    }

    public List<SitiosManuales> getSitiosManuales() {
        return sitiosManuales;
    }

    public void setSitiosManuales(List<SitiosManuales> sitiosManuales) {
        this.sitiosManuales = sitiosManuales;
    }

    //public Sitios getSitio() {
     //   return sitio;
    //}

    //public void setSitio(Sitios sitio) {
    //    this.sitio = sitio;
    //}

    //</editor-fold>

    //<editor-fold desc="Setters">
    //</editor-fold>


}
