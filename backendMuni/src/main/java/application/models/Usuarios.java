package application.models;

import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuarios {
    @Id
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "contrasenia", length = 100)
    private String contrasenia;

   // @OneToOne(mappedBy = "documento")
   // @JsonIgnore
    // private List<Duenio> duenios;

    public Usuarios(){

    }

    public Usuarios(String documento, String email, String contrasenia){
        this.documento= documento;
        this.email= email;
        this.contrasenia= contrasenia;
    }

    //<editor-fold desc="Getters">
    public String getDocumento(){
        return documento;
    }
    public String getEmail(){
        return this.email;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setDocumento(String documento){
        this.documento = documento;
    }
    public void setMail(String mail){
        this.email = mail;
    }
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }



}
