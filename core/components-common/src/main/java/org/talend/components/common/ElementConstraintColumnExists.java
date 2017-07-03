package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;

/**
 * Constraint on a column existence
 *
 */
public abstract class ElementConstraintColumnExists extends ElementConstraint {

    /**
     * Name of the column to be checked
     */
    private String columnName;

    public static final String ERROR_MESSAGE = "column $1%s not found";

    public ElementConstraintColumnExists(String columnName) {
        super("Column must exists in the schema");
        this.columnName = columnName;
    }

    @Override
    public abstract void validate(IndexedRecord input, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException;

}
