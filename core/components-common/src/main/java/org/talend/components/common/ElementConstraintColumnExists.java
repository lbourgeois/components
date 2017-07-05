package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.constraint.ElementConstraint;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;

/**
 * Constraint on a column existence
 *
 */
public abstract class ElementConstraintColumnExists implements ElementConstraint {

    public static final String ERROR_MESSAGE = "column %s not found";

    @Override
    public abstract void validate(IndexedRecord input, ComponentProperties properties)
            throws ConstraintViolationException;

}
