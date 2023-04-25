package fr.uga.l3miage.example.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class PartieRepository implements CRUDRepository<Long, Partie> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Partie save(Partie partie) {
        entityManager.persist(partie);
        return partie;
    }

    @Override
    public Partie get(Long id) {
        return entityManager.find(Partie.class, id);
    }

    @Override
    public void delete(Partie partie) {
        entityManager.remove(partie);
    }

    @Override
    public List<Partie> all() {
        return entityManager.createQuery("SELECT p FROM Partie p ORDER BY id", Partie.class).getResultList();

    }
}
