package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;


public abstract class ElementConstraintNumericValueWithInequalityOperator extends ElementConstraint {

    public static final String ERROR_MESSAGE = "Column $1%s must be numeric : $2%s";

    @Override
    public abstract void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException;

}
