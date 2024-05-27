package old;

import jakarta.persistence.*;

@Entity
@Table(name="Model")
public class Model {
    @Id
    @Column(name = "PKString", length = 20, nullable = false)
    private String pk;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PKInteger")
    private Integer id;

    public Model(){

    }

    public Model(String pk, Integer id){
        this.pk = pk;
        this.id = id;
    }

    //<editor-fold desc="Getters">
    public String getPk(){
        return this.pk;
    }
    public Integer getId(){
        return this.id;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setPk(String pk){
        this.pk = pk;
    }
    //</editor-fold>


}
