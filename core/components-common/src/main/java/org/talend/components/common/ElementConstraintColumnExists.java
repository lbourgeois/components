package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;

/**
 * Constraint on a column existence
 *
 */
public abstract class ElementConstraintColumnExists extends ElementConstraint {

    public static final String ERROR_MESSAGE = "column $1%s not found";

    @Override
    public abstract void validate(IndexedRecord input, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException;

}
