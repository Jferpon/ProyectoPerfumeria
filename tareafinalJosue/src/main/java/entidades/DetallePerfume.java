package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Josué
 */
@Entity
@Table(name = "detallePerfume")
//Declaro las namedQuery una para encontrar todo, por id suyo, por idPerfume y por idPerfume e idNota
@NamedQueries({
    @NamedQuery(name = "DetallePerfume.findAll", query = "SELECT d FROM DetallePerfume d"),
    @NamedQuery(name = "DetallePerfume.findById", query = "SELECT d FROM DetallePerfume d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "DetallePerfume.findByIdPerfume", query = "SELECT d FROM DetallePerfume d WHERE d.perfume.idPerfume = :idPerfume"),
    @NamedQuery(name = "DetallePerfume.findByPerfumeYNota", query = "SELECT d FROM DetallePerfume d WHERE d.perfume.idPerfume = :idPerfume AND d.notaAromatica.idNota = :idNota"
    )

})
public class DetallePerfume implements Serializable {

    //Al ser una tabla intermedia entre una relacion N:M necesito dos ManytoOne con sus id
    //Atributos encapsulados
    @Id
    @Basic
    @Column(name = "idDetalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "idPerfume")
    private Perfume perfume;

    @ManyToOne()
    @JoinColumn(name = "idNota")
    private NotaAromatica notaAromatica;

    
    //Constructores por defecto y con parametros 
    public DetallePerfume() {
    }

    public DetallePerfume(Integer idDetalle, Perfume perfume, NotaAromatica notaAromatica) {
        this.idDetalle = idDetalle;
        this.perfume = perfume;
        this.notaAromatica = notaAromatica;
    }

   
    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Perfume getPerfume() {
        return perfume;
    }

    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
    }

    public NotaAromatica getNotaAromatica() {
        return notaAromatica;
    }

    public void setNotaAromatica(NotaAromatica notaAromatica) {
        this.notaAromatica = notaAromatica;
    }

    @Override
    public String toString() {
        return "DetallePerfume{"
                + "idDetalle=" + idDetalle
                + ", perfume=" + (perfume != null ? perfume.getNombrePerfume() : "null")
                + ", notaAromatica=" + (notaAromatica != null ? notaAromatica.getOlor() : "null")
                + '}';
    }
}
