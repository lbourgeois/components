package org.talend.components.api.exception;


/**
 * Exception raised when a constraint validation fails
 */
public class ConstraintViolationException extends Exception {

    public ConstraintViolationException(String message) {
        super(message);
    }

}
