package org.talend.components.common;

/**
 * Single constraint which can be applied to a pipeline element
 */
public interface ElementConstraint {

    /**
     * Validation method
     * 
     * @param o element to validate
     * @param properties component properties validation could depend on
     */
    public void validate(Object o, FixedConnectorsComponentProperties properties);

}
