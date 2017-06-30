package org.talend.components.processing.runtime.filterrow;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.common.ConstraintViolationException;
import org.talend.components.common.ElementConstraintNumericValueWithInequalityOperator;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.common.IndexedRecordUtil;
import org.talend.components.processing.filterrow.FilterRowProperties;

public class FilterRowConstraintNumericValueWithInequalityOperator extends ElementConstraintNumericValueWithInequalityOperator {

    @Override
    public void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException {

        String operator = ((FilterRowProperties) properties).operator.getValue();
        String columnName = ((FilterRowProperties) properties).columnName.getValue();
        // TODO Check if this list can be found somewhere else
        ArrayList inequalityOperators = new ArrayList(Arrays.asList("<", "<=", ">", ">="));
        if (inequalityOperators.contains(operator)) {
            Object value = IndexedRecordUtil.getField(columnName, element);
            if (!(value instanceof Number)) {
                // TODO Externalize exception message
                throw new ConstraintViolationException(String.format("Column value is not numeric : $1%s", value));
            }
        }
    }

}
