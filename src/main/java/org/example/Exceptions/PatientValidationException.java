package org.example.Exceptions;

public class PatientValidationException extends RuntimeException {
    public PatientValidationException(String message) {
        super(message);
    }
}
