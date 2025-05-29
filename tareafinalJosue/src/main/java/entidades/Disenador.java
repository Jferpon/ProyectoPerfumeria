package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Josué
 */
@Entity
@Table(name = "disenadores")
@NamedQueries({
        @NamedQuery(name = "Disenador.findAll", query = "SELECT d FROM Disenador d"),
        @NamedQuery(name = "Disenador.findById", query = "SELECT d FROM Disenador d WHERE d.id = :id"),
        @NamedQuery(name = "Disenador.findByNombre", query = "SELECT d FROM Disenador d WHERE d.nombre = :nombre")
})
public class Disenador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "ape1")
    private String ape1;

    @Basic
    @Column(name = "ape2")
    private String ape2;

    @Basic
    @Column(name = "fecNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;

    @OneToMany(mappedBy = "disenador", cascade = CascadeType.PERSIST)
    private Collection<Perfume> perfumeCollection;

    public Disenador() {
        this.perfumeCollection = new ArrayList<>();
    }

    public Disenador(String nombre, String ape1, String ape2, Date fecNacimiento) {
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.fecNacimiento = fecNacimiento;
        this.perfumeCollection = new ArrayList<>();
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public Collection<Perfume> getPerfumeCollection() {
        return perfumeCollection;
    }

    public void setPerfumeCollection(Collection<Perfume> perfumeCollection) {
        this.perfumeCollection = perfumeCollection;
        for (Perfume p : perfumeCollection) {
            p.setDisenador(this);
        }
    }

    public void addPerfume(Perfume perfume) {
        this.perfumeCollection.add(perfume);
        perfume.setDisenador(this);
    }

    public void removePerfume(Perfume perfume) {
        this.perfumeCollection.remove(perfume);
        perfume.setDisenador(null);
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Perfume perfume : perfumeCollection) {
            tmp += perfume + "\n";
        }
        return "Disenador{" + "id=" + id + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2
                + ", fecNacimiento=" + fecNacimiento
                + ", perfumeCollection=\n" + tmp + '}';
    }
}