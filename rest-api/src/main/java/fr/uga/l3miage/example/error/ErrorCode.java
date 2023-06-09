package fr.uga.l3miage.example.error;


/**
 * Représente les codes d'erreur possibles pour une errorResponse
 * Va permettre d'être le discriminant lors de la deserialization des erreurs
 */
public enum ErrorCode {
    TEST_INT_IS_ZERO_ERROR,
    IS_NOT_TEST_ERROR,
    TEST_IS_NOT_FOUND,
    ENSEIGNANT_IS_NOT_FOUND,
    PARTIE_IS_NOT_FOUND,
    PARTICIPANT_IS_NOT_FOUND,
    MIAHOOT_IS_NOT_FOUND,
    QUESTION_IS_NOT_FOUND,
    REPONSE_IS_NOT_FOUND,
    DESCRIPTION_ALREADY_USE_ERROR,
    MAIL_ALREADY_USE_ERROR,
    TEST_ENTITY_NOT_DELETED_ERROR,
    ENSEIGNANT_ENTITY_NOT_DELETED_ERROR,
    PARTICIPANT_ENTITY_NOT_DELETED_ERROR,
    QUESTION_ENTITY_NOT_DELETED_ERROR,
    REPONSE_ENTITY_NOT_DELETED_ERROR,
}
