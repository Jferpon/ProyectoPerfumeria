package controladores;

import entidades.DetallePerfume;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
/**
 *
 * @author Josué
 */

public class DetallePerfumeController {

    private final EntityManagerFactory emf;

    public DetallePerfumeController() {
        this.emf = Persistence.createEntityManagerFactory("perfumes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo detalle de perfume en la base de datos.
     */
    public void create(DetallePerfume detalle) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(detalle);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Ocurrió un error al crear el detalle de perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un detalle de perfume por su ID.
     */
    public DetallePerfume findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallePerfume.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Devuelve todos los detalles de perfume.
     */
    public List<DetallePerfume> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("DetallePerfume.findAll", DetallePerfume.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un detalle de perfume existente.
     */
    public void update(DetallePerfume detalle) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(detalle);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el detalle de perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un detalle de perfume por ID.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DetallePerfume detalle = em.find(DetallePerfume.class, id);
            if (detalle != null) {
                em.remove(detalle);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el detalle de perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina todos los detalles de perfume y reinicia el AUTO_INCREMENT.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("DELETE FROM detallePerfume").executeUpdate();
            em.createNativeQuery("ALTER TABLE BDPerfumeria.detallePerfume AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los detalles de perfume", ex);
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
