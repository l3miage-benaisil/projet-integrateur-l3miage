package fr.uga.l3miage.example.error.notFoundErrorResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.uga.l3miage.example.error.ErrorCode;
import fr.uga.l3miage.example.error.errorResponse.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;


import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@JsonTypeName(ReponseNotFoundErrorResponse.TYPE_NAME)
@Getter
@ToString(callSuper = true, exclude = "errorCodeSwaggerDocumentation")
@EqualsAndHashCode(callSuper = true)
public class ReponseNotFoundErrorResponse extends ErrorResponse {

    protected static final String TYPE_NAME = "REPONSE_ENTITY_NOT_DELETED_ERROR";
    @Schema(name = "errorCode", description = "Ce code d'erreur est aussi le discriminant pour le polymorphisme", allowableValues = TYPE_NAME,
            implementation = String.class, accessMode = Schema.AccessMode.READ_WRITE)
    @JsonProperty(access = WRITE_ONLY)
    private final String errorCodeSwaggerDocumentation = "Field used only to generate documentation, don't use it";

    @Schema(description = "l'id utilisé pour la recherche",example = "1")
    private final Long id;

    @Builder
    @Jacksonized
    public ReponseNotFoundErrorResponse(String uri, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage, Long id) {
        super(uri, httpStatus, errorCode, errorMessage);
        this.id = id;
    }

}
