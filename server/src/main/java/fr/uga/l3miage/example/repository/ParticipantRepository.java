package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findById(final Long id);
    void deleteById(final Long id);

    List<Participant> findAllByPartie(final Partie partie);
    Optional<Participant> findByPartieAndId(final Partie partie, final Long id);

}
