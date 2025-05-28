package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Josué
 */

@Entity
@Table(name = "detallePerfume")
public class DetallePerfume implements Serializable {

    @Id
    @Basic
    @Column(name = "idDetalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "idPerfume")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "idNota")
    private NotaAromatica notaAromatica;

    // Getters y setters

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
        return "DetallePerfume{" +
                "idDetalle=" + idDetalle +
                ", perfume=" + (perfume != null ? perfume.getNombrePerfume() : "null") +
                ", notaAromatica=" + (notaAromatica != null ? notaAromatica.getOlor(): "null") +
                '}';
    }
}