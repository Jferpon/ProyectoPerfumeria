package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Josué
 */
@Entity
@Table(name = "notasAromaticas")
//NamedQueries que encuentran todo, por id y por Olor 
@NamedQueries({
    @NamedQuery(name = "NotaAromatica.findAll", query = "SELECT d FROM NotaAromatica d"),
    @NamedQuery(name = "NotaAromatica.findById", query = "SELECT d FROM NotaAromatica d WHERE d.idNota = :idNota"),
    @NamedQuery(name = "NotaAromatica.findByOlor", query = "SELECT d FROM NotaAromatica d WHERE d.olor = :olor")
})
public class NotaAromatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNota")
    private Integer idNota;

    // Aquí se aplica el converter al tipoNota porque es enum, no String 
    @Convert(converter = TipoNotaConverter.class)
    @Column(name = "tipoNota")
    private TipoNota tipoNota;

    @Column(name = "olor")
    private String olor;

    @OneToMany(mappedBy = "notaAromatica", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<DetallePerfume> detalles = new ArrayList<>();

    //Constructores por defecto y con Parametros
    public NotaAromatica() {
        this.detalles = new ArrayList<>();
    }

    public NotaAromatica(Integer idNota, TipoNota tipoNota, String olor) {
        this.idNota = idNota;
        this.tipoNota = tipoNota;
        this.olor = olor;
        this.detalles = new ArrayList<>();
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public String getOlor() {
        return olor;
    }

    public void setOlor(String olor) {
        this.olor = olor;
    }

    public Collection<DetallePerfume> getDetalles() {
        return detalles;
    }

    public void setDetalles(Collection<DetallePerfume> detalles) {
        this.detalles = detalles;
    }
    
    

    @Override
    public String toString() {
        return "NotaAromatica{"
                + "idNota=" + idNota
                + ", tipoNota=" + tipoNota
                + ", olor='" + olor + '\''
                + '}';
    }
}
