package org.talend.components.common;


/**
 * Exception raised when a constraint validation fails
 */
public class ConstraintViolationException extends Exception {

    public ConstraintViolationException(String message) {
        super(message);
    }

}
