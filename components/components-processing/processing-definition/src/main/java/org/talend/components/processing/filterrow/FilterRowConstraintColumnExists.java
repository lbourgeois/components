package org.talend.components.processing.filterrow;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.ElementConstraintColumnExists;
import org.talend.components.common.IndexedRecordUtil;

public class FilterRowConstraintColumnExists extends ElementConstraintColumnExists {

    @Override
    public void validate(IndexedRecord input, ComponentProperties properties) throws ConstraintViolationException {
        String columnName = ((FilterRowProperties) properties).columnName.getValue();
        if (!IndexedRecordUtil.hasColumn(input, columnName)) {
            throw new ConstraintViolationException(String.format(ElementConstraintColumnExists.ERROR_MESSAGE, columnName));
        }
    }
}
