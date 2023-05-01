package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.EnseignantEndpoint;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.service.EnseignantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnseignantController implements EnseignantEndpoint {

    public final EnseignantService enseignantService;


    @Override
    public void createEntityEnseignant(CreateEnseignantRequest request) {
        enseignantService.createEnseignant(request);
    }

    @Override
    public EnseignantDTO getEntityEnseignantByMail(final String mail) throws Exception {
        return enseignantService.getEnseignantByMail(mail);
    }

    @Override
    public List<EnseignantDTO> getAllEnseignants() throws Exception {
        return enseignantService.getAllEnseignants();
    }

    @Override
    public void createEntityMiahootFromEnseignant(String mail, CreateMiahootRequest request) throws Exception {
        enseignantService.createMiahootFromEnseignant(mail,request);
    }

    @Override
    public void deleteEnseignantEntity(String mail) {
        enseignantService.deleteEnseignantByMail(mail);
    }

    @Override
    public void updateEnseignantEntity(String mail, EnseignantDTO enseignant) {
        enseignantService.updateEnseignant(mail, enseignant);
    }

    @Override
    public void addQuestionToMiahoot(String mail, Long idMiahoot, CreateQuestionRequest createQuestionRequest) throws Exception {
        enseignantService.addQuestionToMiahoot(mail, idMiahoot , createQuestionRequest);
    }

    @Override
    public List<MiahootDTO> getAllMiahootsOfEnseignant(String mail) throws Exception {
        return enseignantService.getAllMiahootsOfEnseignant(mail);
    }


}
