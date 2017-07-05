package org.talend.components.processing.filterrow;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.ElementConstraintNumericValueWithInequalityOperator;
import org.talend.components.common.IndexedRecordUtil;

public class FilterRowConstraintNumericValueWithInequalityOperator extends ElementConstraintNumericValueWithInequalityOperator {

    @Override
    public void validate(IndexedRecord element, ComponentProperties properties) throws ConstraintViolationException {

        String operator = ((FilterRowProperties) properties).operator.getValue();
        String columnName = ((FilterRowProperties) properties).columnName.getValue();
        ArrayList inequalityOperators = new ArrayList(
                Arrays.asList(ConditionsRowConstant.Operator.LOWER, ConditionsRowConstant.Operator.LOWER_OR_EQUAL,
                        ConditionsRowConstant.Operator.GREATER, ConditionsRowConstant.Operator.GREATER_OR_EQUAL));
        if (inequalityOperators.contains(operator)) {
            Object value = IndexedRecordUtil.getField(columnName, element);
            if (!(value instanceof Number)) {
                try {
                    Float.valueOf(value.toString());
                } catch (NumberFormatException nfe) {
                    throw new ConstraintViolationException(
                            String.format(ElementConstraintNumericValueWithInequalityOperator.ERROR_MESSAGE, columnName, value));
                }
            }
        }
    }

}
