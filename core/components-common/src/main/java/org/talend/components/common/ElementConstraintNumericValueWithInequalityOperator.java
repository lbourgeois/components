package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.constraint.ElementConstraint;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;


/**
 * Constraint on numeric data when using inequality operator
 */
public abstract class ElementConstraintNumericValueWithInequalityOperator implements ElementConstraint {

    public static final String ERROR_MESSAGE = "Column $1%s must be numeric : $2%s";

    @Override
    public abstract void validate(IndexedRecord element, ComponentProperties properties)
            throws ConstraintViolationException;

}
