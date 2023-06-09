package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ReponseEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.QuestionEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReponseService {
    private final EnseignantComponent enseignantComponent;
    private final MiahootComponent miahootComponent;
    private final QuestionComponent questionComponent;
    private final ReponseComponent reponseComponent;
    private final ReponseMapper reponseMapper;


    public void addReponseToQuestionOfMiahoot(final String uidEnseignant, final Long idMiahoot, final Long idQuestion, final CreateReponseRequest createReponseRequest) throws Exception {
        Reponse newReponse = reponseMapper.toEntity(createReponseRequest);
        reponseComponent.addReponseToQuestionOfMiahoot(uidEnseignant, idMiahoot, idQuestion, newReponse);
    }


    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return reponseMapper.toDto(reponseComponent.getAllReponsesOfQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion));
    }


    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        try {
            return reponseMapper.toDto(reponseComponent.getReponseOfQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion, idReponse));
        } catch (ReponseEntityNotFoundException e) {
            throw new ReponseEntityNotFoundRestException(e.getMessage(), idReponse, e);
        }
    }


    /**
     * Efface une reponse d'une question d'un miahoot d'un enseignant
     * @param uidEnseignant
     * @param idMiahoot
     * @param idQuestion
     * @param idReponse
     */
    public void deleteReponseOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        try {
            Question question = questionComponent.getQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion);
            Reponse reponse = reponseComponent.getReponseOfQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion, idReponse);
            reponseComponent.deleteReponseOfQuestion(question, reponse);
        } catch (QuestionEntityNotFoundException | ReponseEntityNotFoundException e) {
            throw new ReponseEntityNotDeletedRestException(e.getMessage(), e);
        }
    }

}
