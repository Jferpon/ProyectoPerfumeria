package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Josué
 */
@Entity
@Table(name = "perfumes")
public class Perfume implements Serializable {

    @Id
    @Basic
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

    @ManyToOne
    @JoinColumn(name = "idDisenador")
    private Disenador disenador;

    @OneToMany(mappedBy = "perfume")
    private Collection<DetallePerfume> detallePerfumeCollection;

    // Getters y setters

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
        return "Perfume{" +
                "idPerfume=" + idPerfume +
                ", nombrePerfume=" + nombrePerfume +
                ", nombreLinea=" + nombreLinea +
                ", precio=" + precio +
                ", horasDuracion=" + horasDuracion +
                ", cantidadML=" + cantidadML +
                ", disenador=" + (disenador != null ? disenador.getNombre(): "null") +
                ", detallePerfumeCollection=\n" + tmp +
                '}';
    }
}