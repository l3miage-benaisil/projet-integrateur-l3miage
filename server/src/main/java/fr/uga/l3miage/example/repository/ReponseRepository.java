package fr.uga.l3miage.example.repository;
import fr.uga.l3miage.example.models.Reponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReponseRepository implements CRUDRepository<Long, Reponse> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reponse save(Reponse reponse) {
        entityManager.persist(reponse);
        return reponse;
    }

    @Override
    public Reponse get(Long id) {
        return entityManager.find(Reponse.class, id);
    }

    @Override
    public void delete(Reponse reponse) {
        entityManager.remove(reponse);
    }

    @Override
    public List<Reponse> all() {
        return entityManager.createQuery("SELECT r FROM Reponse r ORDER BY id", Reponse.class).getResultList();

    }
}
