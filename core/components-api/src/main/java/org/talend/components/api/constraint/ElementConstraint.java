package org.talend.components.api.constraint;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;

/**
 * Single constraint which can be applied to a pipeline element
 */
public interface ElementConstraint {

    /**
     * Validation method
     * 
     * @param element element to validate
     * @param properties component properties validation could depend on
     */
    abstract void validate(IndexedRecord element, ComponentProperties properties)
            throws ConstraintViolationException;
}
