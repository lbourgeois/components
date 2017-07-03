package org.talend.components.processing.runtime.filterrow;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.common.ConstraintViolationException;
import org.talend.components.common.ElementConstraintNumericValueWithInequalityOperator;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.common.IndexedRecordUtil;
import org.talend.components.processing.filterrow.ConditionsRowConstant;
import org.talend.components.processing.filterrow.FilterRowProperties;

public class FilterRowConstraintNumericValueWithInequalityOperator extends ElementConstraintNumericValueWithInequalityOperator {

    @Override
    public void validate(IndexedRecord element, FixedConnectorsComponentProperties properties)
            throws ConstraintViolationException {

        String operator = ((FilterRowProperties) properties).operator.getValue();
        String columnName = ((FilterRowProperties) properties).columnName.getValue();
        ArrayList inequalityOperators = new ArrayList(
                Arrays.asList(ConditionsRowConstant.Operator.LOWER, ConditionsRowConstant.Operator.LOWER_OR_EQUAL,
                        ConditionsRowConstant.Operator.GREATER, ConditionsRowConstant.Operator.GREATER_OR_EQUAL));
        if (inequalityOperators.contains(operator)) {
            Object value = IndexedRecordUtil.getField(columnName, element);
            if (!(value instanceof Number)) {
                throw new ConstraintViolationException(
                        String.format(ElementConstraintNumericValueWithInequalityOperator.ERROR_MESSAGE, columnName, value));
            }
        }
    }

}
