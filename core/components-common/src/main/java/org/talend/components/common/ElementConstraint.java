package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;

/**
 * Single constraint which can be applied to a pipeline element
 */
abstract class ElementConstraint {

    /**
     * Validation method
     * 
     * @param element element to validate
     * @param properties component properties validation could depend on
     */
    abstract void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException;
}
