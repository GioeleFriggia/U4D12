package gioelefriggia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EventoDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GestioneEventiPU");

    public void save(Evento evento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(evento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    public Evento getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Evento evento = null;
        try {
            evento = entityManager.find(Evento.class, id);
        } finally {
            entityManager.close();
        }
        return evento;
    }

    public void delete(Evento evento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(evento)) {
                evento = entityManager.merge(evento);
            }
            entityManager.remove(evento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
}
