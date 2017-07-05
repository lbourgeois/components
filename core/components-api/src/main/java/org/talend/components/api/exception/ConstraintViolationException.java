package org.talend.components.api.exception;

import org.talend.components.api.exception.error.ComponentsErrorCode;
import org.talend.daikon.exception.ExceptionContext;
import org.talend.daikon.exception.TalendRuntimeException;

/**
 * Exception raised when a constraint validation fails
 */
public class ConstraintViolationException extends TalendRuntimeException {

    public ConstraintViolationException(String message) {
        super(ComponentsErrorCode.CONSTRAINT_EXCEPTION, ExceptionContext.build().put(ExceptionContext.KEY_MESSAGE, message));
    }

}
