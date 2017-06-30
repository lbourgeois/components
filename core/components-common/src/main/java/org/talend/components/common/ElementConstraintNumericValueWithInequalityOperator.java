package org.talend.components.common;

import org.apache.avro.generic.IndexedRecord;


public class ElementConstraintNumericValueWithInequalityOperator extends ElementConstraint {

    public ElementConstraintNumericValueWithInequalityOperator(String description) {
        super(description);
    }

    @Override
    void validate(IndexedRecord element, FixedConnectorsComponentProperties properties) {
        // TODO Auto-generated method stub

    }

}
