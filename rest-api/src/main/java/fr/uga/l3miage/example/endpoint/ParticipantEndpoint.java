package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.notFoundErrorResponse.ParticipantNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.PartieNotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Endpoints des Participants")
@CrossOrigin
@RestController
@RequestMapping("api/")
public interface ParticipantEndpoint {

    @Operation(description = "Création d'une entité Participant dans la Partie du codePartie passé en paramètre")
    @ApiResponse(responseCode = "201", description = "L'entité Participant de la Partie avec le codePartie demandé a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("parties/{codePartie}/participants")
    void createParticipantOfPartie(@PathVariable("codePartie") Long codePartie, @Valid @RequestBody CreateParticipantRequest request);


    @Operation(description = "Récupérer la liste de DTO des entités participants de la partie qui a pour codePartie celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie la liste de DTO des entités participants de la partie demandée",
            content = @Content(schema = @Schema(implementation = ParticipantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité partie demandée n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = PartieNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("parties/{codePartie}/participants")
    List<ParticipantDTO> getAllParticipantsOfPartie(@PathVariable("codePartie") Long codePartie);


    @Operation(description = "Récupérer le DTO de l'entité participant qui a pour id celui passé en paramètre dans la partie avec codePartie demandé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité participant demandée de la partie avec le codePartie demandé",
            content = @Content(schema = @Schema(implementation = ParticipantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité partie n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = PartieNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité participant n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("parties/{codePartie}/participants/{id}")
    ParticipantDTO getParticipantOfPartie(@PathVariable("codePartie") Long codePartie, @PathVariable("id") Long id);


    @Operation(description = "Suppression de tous les participants d'une partie d'un enseignant")
    @ApiResponse(responseCode = "200", description = "si  les elements sont renvoyés et supprimés")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si les entités n'ont pas pu être supprimées",
            content = @Content(schema = @Schema(implementation = ParticipantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("enseignants/{uidEnseignant}/parties/{codePartie}/participants")
    void deleteAllParticipantsOfPartie(@PathVariable("uidEnseignant") String uidEnseignant, @PathVariable("codePartie") Long codePartie);
}
