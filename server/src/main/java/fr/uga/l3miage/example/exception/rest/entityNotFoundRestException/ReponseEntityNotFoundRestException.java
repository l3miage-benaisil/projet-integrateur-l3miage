package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Correspond à l'exception d'API d'une entité non trouvée<br>
 */

@Getter
public class ReponseEntityNotFoundRestException extends RuntimeException {
    private final Long id;

    public ReponseEntityNotFoundRestException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public ReponseEntityNotFoundRestException(String message, Long id, Throwable cause){
        super(message,cause);
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
    public ErrorCode getErrorCode(){return ErrorCode.REPONSE_IS_NOT_FOUND;}
}
