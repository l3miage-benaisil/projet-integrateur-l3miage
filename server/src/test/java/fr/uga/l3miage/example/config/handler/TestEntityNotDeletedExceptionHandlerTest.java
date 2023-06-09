package fr.uga.l3miage.example.config.handler;

import fr.uga.l3miage.example.config.handler.entityNotDeletedExceptionHandler.TestEntityNotDeletedExceptionHandler;
import fr.uga.l3miage.example.error.errorResponse.ErrorResponse;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.TestEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.TestEntityNotDeletedRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Permet de tester le handler de l'exception d'API {@link TestEntityNotDeletedRestException}
 */
class TestEntityNotDeletedExceptionHandlerTest {

    @Test
    void testHandle() {
        TestEntityNotDeletedExceptionHandler handler = new TestEntityNotDeletedExceptionHandler();
        TestEntityNotDeletedRestException exception = new TestEntityNotDeletedRestException("Entity not be deleted");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("TestConfigWithProperties");
        ResponseEntity<TestEntityNotDeletedErrorResponse> expected = ResponseEntity.status(exception.getHttpStatus())
                .body(TestEntityNotDeletedErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .uri(request.getRequestURI())
                        .errorCode(exception.getErrorCode())
                        .httpStatus(exception.getHttpStatus())
                        .build());

        ResponseEntity<ErrorResponse> response = handler.handle(request, exception);
        assertThat(response).usingRecursiveComparison().isEqualTo(expected);
    }
}
