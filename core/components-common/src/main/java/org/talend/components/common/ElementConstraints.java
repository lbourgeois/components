package org.talend.components.common;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.avro.generic.IndexedRecord;

/**
 * Contraints set that can be applied to a PCollection element
 *
 */
public class ElementConstraints {

    private Collection<ElementConstraint> constraints = new ArrayList<ElementConstraint>();
    
    public ElementConstraints add(ElementConstraint e) {
        this.add(e);
        return this;
    }
    
    /**
     * Validate that an object satisfy all constraints
     * 
     * @param o element to validate
     * @param properties component properties
     * @throws ConstraintViolationException if the element do not satisfy a constraint
     */
    public void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException {

        for (ElementConstraint elementConstraint : constraints) {
            elementConstraint.validate(element, properties);
        }
    }
}
