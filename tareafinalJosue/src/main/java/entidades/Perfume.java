package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Josué
 */
@Entity
@Table(name = "perfumes")
//NamedQueries que encuentran todo, encuentran segun Id, encuentran segun el idDisenador y encuentran segun nombre
@NamedQueries({
    @NamedQuery(name = "Perfume.findAll", query = "SELECT d FROM Perfume d"),
    @NamedQuery(name = "Perfume.findById", query = "SELECT d FROM Perfume d WHERE d.idPerfume = :idPerfume"),
    @NamedQuery(name = "Perfume.findByFkId", query = "SELECT d FROM Perfume d WHERE d.disenador = :disenador"),
    @NamedQuery(name = "Perfume.findByNombre", query = "SELECT d FROM Perfume d WHERE d.nombrePerfume = :nombrePerfume")
})
public class Perfume implements Serializable {

    //Declaracion de atributos encapsulados y relaciones entre la tabla intermedia y la de disenadores
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerfume")
    private Integer idPerfume;

    @Basic
    @Column(name = "nombrePerfume")
    private String nombrePerfume;

    @Basic
    @Column(name = "nombreLinea")
    private String nombreLinea;

    @Basic
    @Column(name = "precio")
    private Double precio;

    @Basic
    @Column(name = "horasDuracion")
    private Integer horasDuracion;

    @Basic
    @Column(name = "cantidadML")
    private Integer cantidadML;

    @Convert(converter = TipoPerfumeConverter.class)
    @Column(name = "tipoPerfume")
    private TipoPerfume tipoPerfume;

    @ManyToOne
    @JoinColumn(name = "idDisenador")
    private Disenador disenador;

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.REMOVE, orphanRemoval = true )
    private Collection<DetallePerfume> detallePerfumeCollection;

    //Constructores, por defecto y con parametros
    public Perfume() {
        this.detallePerfumeCollection = new ArrayList<>();
    }

    public Perfume(Integer idPerfume, String nombrePerfume, String nombreLinea, Double precio, Integer horasDuracion, Integer cantidadML, TipoPerfume tipoPerfume, Disenador disenador, Collection<DetallePerfume> detallePerfumeCollection) {
        this.idPerfume = idPerfume;
        this.nombrePerfume = nombrePerfume;
        this.nombreLinea = nombreLinea;
        this.precio = precio;
        this.horasDuracion = horasDuracion;
        this.cantidadML = cantidadML;
        this.tipoPerfume = tipoPerfume;
        this.disenador = disenador;
        this.detallePerfumeCollection = new ArrayList<>();
    }

    
    
    public Integer getIdPerfume() {
        return idPerfume;
    }

    public void setIdPerfume(Integer idPerfume) {
        this.idPerfume = idPerfume;
    }

    public String getNombrePerfume() {
        return nombrePerfume;
    }

    public void setNombrePerfume(String nombrePerfume) {
        this.nombrePerfume = nombrePerfume;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getHorasDuracion() {
        return horasDuracion;
    }

    public void setHorasDuracion(Integer horasDuracion) {
        this.horasDuracion = horasDuracion;
    }

    public Integer getCantidadML() {
        return cantidadML;
    }

    public void setCantidadML(Integer cantidadML) {
        this.cantidadML = cantidadML;
    }

    public TipoPerfume getTipoPerfume() {
        return tipoPerfume;
    }

    public void setTipoPerfume(TipoPerfume tipoPerfume) {
        this.tipoPerfume = tipoPerfume;
    }

    public Disenador getDisenador() {
        return disenador;
    }

    public void setDisenador(Disenador disenador) {
        this.disenador = disenador;
    }

    public Collection<DetallePerfume> getDetallePerfumeCollection() {
        return detallePerfumeCollection;
    }

    public void setDetallePerfumeCollection(Collection<DetallePerfume> detallePerfumeCollection) {
        this.detallePerfumeCollection = detallePerfumeCollection;
    }

    @Override
    public String toString() {
        String tmp = "";
        if (detallePerfumeCollection != null) {
            for (DetallePerfume detalle : detallePerfumeCollection) {
                tmp += detalle + "\n";
            }
        }
        return "Perfume{"
                + "idPerfume=" + idPerfume
                + ", nombrePerfume=" + nombrePerfume
                + ", nombreLinea=" + nombreLinea
                + ", precio=" + precio
                + ", horasDuracion=" + horasDuracion
                + ", cantidadML=" + cantidadML
                + ", disenador=" + (disenador != null ? disenador.getNombre() : "null")
                + ", detallePerfumeCollection=\n" + tmp
                + ", tipoPerfume=" + tipoPerfume
                + '}';
    }
}
