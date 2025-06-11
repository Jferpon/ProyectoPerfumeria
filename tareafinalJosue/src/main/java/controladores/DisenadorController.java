package controladores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Disenador;

/**
 *
 * @author Josué
 */
public class DisenadorController {

    private final EntityManagerFactory emf;

    public DisenadorController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("perfumes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo diseñador en la base de datos.
     * 
     * @param disenador El diseñador a crear.
     */
    public void create(Disenador disenador) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(disenador);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Ocurrio un error al crear al disenador", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un diseñador por su ID.
     * 
     * @param id El ID del diseñador.
     * @return El diseñador encontrado o null si no existe.
     */
    public Disenador findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disenador.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los diseñadores de la base de datos.
     * 
     * @return Una lista de diseñadores.
     */
   public List<Disenador> findAll() {
    EntityManager em = getEntityManager();
    try {
        return em.createNamedQuery("Disenador.findAll", Disenador.class).getResultList();
    } finally {
        em.close();
    }
}

    /**
     * Actualiza un diseñador existente.
     * 
     * @param disenador El diseñador a actualizar.
     */
    public void update(Disenador disenador) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(disenador);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al actualizar el disenador", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un diseñador por su ID.
     * 
     * @param id El ID del diseñador a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        Disenador d = em.find(Disenador.class, id);

        // Cargar explícitamente los perfumes si no están cargados
        d.getPerfumeCollection().size();

        em.remove(d); 
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
    }

    /**
     * Elimina todos los diseñadores y reinicia el AUTO_INCREMENT.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("DELETE FROM disenadores").executeUpdate();
            em.createNativeQuery("ALTER TABLE BDPerfumeria.disenadores AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al borrar todos los disenadores", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory.
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}


