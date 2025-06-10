package controladores;

import entidades.DetallePerfume;
import entidades.NotaAromatica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 *
 * @author Josué
 */
public class NotaAromaticaController {

    private final EntityManagerFactory emf;

    public NotaAromaticaController() {
        // Nombre de la unidad de persistencia definido en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("perfumes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea una nueva nota aromática en la base de datos.
     *
     * @param nota La nota aromática a crear.
     */
    public void create(NotaAromatica nota) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nota);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Ocurrió un error al crear la nota aromática", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca una nota aromática por su ID.
     *
     * @param id El ID de la nota.
     * @return La nota encontrada o null si no existe.
     */
    public NotaAromatica findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaAromatica.class, id);
        } finally {
            em.close();
        }
    }

    public List<NotaAromatica> findNotasByIdPerfume(int idPerfume) {
        EntityManager em = getEntityManager();
        List<DetallePerfume> detalles = em.createNamedQuery("DetallePerfume.findByIdPerfume", DetallePerfume.class)
                .setParameter("idPerfume", idPerfume)
                .getResultList();

        List<NotaAromatica> notas = new ArrayList<>();
        for (DetallePerfume dp : detalles) {
            notas.add(dp.getNotaAromatica());
        }

        return notas;
    }

    /**
     * Obtiene todas las notas aromáticas de la base de datos.
     *
     * @return Una lista de notas aromáticas.
     */
    public List<NotaAromatica> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("NotaAromatica.findAll", NotaAromatica.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una nota aromática existente.
     *
     * @param nota La nota a actualizar.
     */
    public void update(NotaAromatica nota) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(nota);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al actualizar la nota aromática", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina una nota aromática por su ID.
     *
     * @param id El ID de la nota a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            NotaAromatica nota = em.find(NotaAromatica.class, id);
            if (nota != null) {
                em.remove(nota);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al borrar una nota aromática", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina todas las notas aromáticas y reinicia el AUTO_INCREMENT.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("DELETE FROM notasAromaticas").executeUpdate();
            em.createNativeQuery("ALTER TABLE BDPerfumeria.notasAromaticas AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al borrar todas las notas aromáticas", ex);
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
