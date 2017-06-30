package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;

/**
 * Constraint on a column existence
 *
 */
public class ElementConstraintColumnExists extends ElementConstraint {

    /**
     * Name of the column to be checked
     */
    private String columnName;

    public ElementConstraintColumnExists(String description, String columnName) {
        super(description);
        this.columnName = columnName;
    }

    @Override
    void validate(IndexedRecord input, FixedConnectorsComponentProperties properties) {
        // TODO get input schema to test if column exists - check in daikon

        // TODO Delete useless properties argument
    }

}
