package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Enseignant;

import java.util.List;

/**
 * Base interface to having consistent naming.
 * @param <I>
 * @param <E>
 */
public interface CRUDRepository<I,E> {
    Enseignant save(Enseignant enseignant);

    Enseignant get(Long id);

    void delete(Enseignant enseignant);

    E save(E entity);

    E get(I id);

    void delete(E entity);

    List<E> all();
}