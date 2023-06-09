package fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class ParticipantEntityNotDeletedRestException extends RuntimeException {
    public ParticipantEntityNotDeletedRestException(String message) {
        super(message);
    }

    public ParticipantEntityNotDeletedRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {return HttpStatus.NOT_FOUND;}

    public ErrorCode getErrorCode(){return ErrorCode.PARTICIPANT_ENTITY_NOT_DELETED_ERROR;}
}
