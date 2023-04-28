package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.repository.MiahootRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final MiahootMapper miahootMapper;


    public Miahoot getMiahoot(final Long id) throws ParticipantEntityNotFoundException {
        return miahootRepository.findById(id)
                .orElseThrow(() -> new ParticipantEntityNotFoundException(String.format("Aucune entité Miahoot n'a été trouvée pour l'id [%d]", id), id));
    }

    public void createMiahoot(final Miahoot entity){
        miahootRepository.save(entity);
    }

    public void deleteMiahoot(final Long id) throws ParticipantEntityNotFoundException {
        try {
            Miahoot deleted = getMiahoot(id);
            miahootRepository.deleteById(id);
        }catch(ParticipantEntityNotFoundException e){
            throw new ParticipantEntityNotFoundException("L'entité Miahoot à supprimer n'a pas été trouvée", id);
        }
    }
}