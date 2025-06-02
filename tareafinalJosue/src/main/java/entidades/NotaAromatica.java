
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author Josué
 */
@Entity
@Table(name = "notasAromaticas")
@NamedQueries({
 @NamedQuery(name = "NotaAromatica.findAll", query = "SELECT d FROM NotaAromatica d"),
    @NamedQuery(name = "NotaAromatica.findById", query = "SELECT d FROM NotaAromatica d WHERE d.idNota = :idNota"),
    @NamedQuery(name = "NotaAromatica.findByOlor", query = "SELECT d FROM NotaAromatica d WHERE d.olor = :olor")
})
public class NotaAromatica implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNota")
    private Integer idNota;

    @Column(name = "tipoNota")
    @Basic(optional = true)
    private String tipoNota;  // 'salida', 'corazon', 'base'

    @Column(name = "olor")
    @Basic(optional = true)
    private String olor;

    public NotaAromatica() {
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public String getOlor() {
        return olor;
    }

    public void setOlor(String olor) {
        this.olor = olor;
    }

    @Override
    public String toString() {
        return "NotaAromatica{" +
                "idNota=" + idNota +
                ", tipoNota='" + tipoNota + '\'' +
                ", olor='" + olor + '\'' +
                '}';
    }
}
