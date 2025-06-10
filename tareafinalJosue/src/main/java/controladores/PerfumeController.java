package controladores;

import entidades.Disenador;
import entidades.Perfume;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Josué
 */
public class PerfumeController {

    private final EntityManagerFactory emf;

    public PerfumeController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("perfumes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo perfume en la base de datos.
     * 
     * @param perfume El perfume a crear.
     */
    public void create(Perfume perfume) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(perfume);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Ocurrió un error al crear el perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un perfume por su ID.
     * 
     * @param id El ID del perfume.
     * @return El perfume encontrado o null si no existe.
     */
    public Perfume findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfume.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los perfumes de la base de datos.
     * 
     * @return Una lista de perfumes.
     */
    public List<Perfume> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("Perfume.findAll", Perfume.class).getResultList();
        } finally {
            em.close();
        }
    }
    public List<Perfume> findByDisenador(int idDisenador) {
    EntityManager em = getEntityManager();
    try {
        // Buscar el diseñador por id
        Disenador disenador = em.find(Disenador.class, idDisenador);
        if (disenador == null) {
            return java.util.Collections.emptyList();
        }
        // Ejecutar la namedQuery con el objeto diseñador
        return em.createNamedQuery("Perfume.findByFkId", Perfume.class)
                 .setParameter("disenador", disenador)
                 .getResultList();
    } finally {
        em.close();
    }
}

    /**
     * Actualiza un perfume existente.
     * 
     * @param perfume El perfume a actualizar.
     */
    public void update(Perfume perfume) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(perfume);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al actualizar el perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un perfume por su ID.
     * 
     * @param id El ID del perfume a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Perfume perfume = em.find(Perfume.class, id);
            if (perfume != null) {
                em.remove(perfume);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al borrar un perfume", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina todos los perfumes y reinicia el AUTO_INCREMENT.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("DELETE FROM perfumes").executeUpdate();
            em.createNativeQuery("ALTER TABLE BDPerfumeria.perfumes AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Hubo un error al borrar todos los perfumes", ex);
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