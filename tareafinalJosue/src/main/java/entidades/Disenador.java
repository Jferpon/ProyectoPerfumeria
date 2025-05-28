package main.java.entidades;

import java.io.Serializable;

@Entity 
@Table(name = "disenadores")

public class Disenador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDisenador")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ape1")
    private String ape1;
    @Basic(optional = false)
    @Column(name = "ape2")
    private String ape2;
   
}
