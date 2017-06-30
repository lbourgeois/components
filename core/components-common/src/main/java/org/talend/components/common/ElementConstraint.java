package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;

/**
 * Single constraint which can be applied to a pipeline element
 */
abstract class ElementConstraint {

    /**
     * Constraint description
     */
    private String description;

    /**
     * Constructor
     * 
     * @param description
     */
    public ElementConstraint(String description) {
        this.description = description;
    }

    /**
     * Validation method
     * 
     * @param element element to validate
     * @param properties component properties validation could depend on
     */
    abstract void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException;

    /**
     * Description getter
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

}
