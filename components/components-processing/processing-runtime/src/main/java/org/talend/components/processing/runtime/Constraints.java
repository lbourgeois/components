package org.talend.components.processing.runtime;


/**
 * Contraints set that can be applied to a PCollection element
 *
 */
public class Constraints {

    /**
     * Validate that an object satisfy all constraints
     * 
     * @param o element to validate
     * @throws ConstraintViolationException if the element do not satisfy a constraint
     */
    public void validate(Object o) throws ConstraintViolationException {

    }

}
