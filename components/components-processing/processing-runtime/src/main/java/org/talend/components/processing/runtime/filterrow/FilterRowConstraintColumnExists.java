package org.talend.components.processing.runtime.filterrow;

import org.apache.avro.generic.IndexedRecord;
import org.talend.components.common.ConstraintViolationException;
import org.talend.components.common.ElementConstraintColumnExists;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.components.common.IndexedRecordUtil;
import org.talend.components.processing.filterrow.FilterRowProperties;

public class FilterRowConstraintColumnExists extends ElementConstraintColumnExists {

    public FilterRowConstraintColumnExists(String columnName) {
        super(columnName);
    }

    @Override
    public void validate(IndexedRecord input, FixedConnectorsComponentProperties properties) throws ConstraintViolationException {
        String columnName = ((FilterRowProperties) properties).columnName.getValue();
        if (!IndexedRecordUtil.hasColumn(input, columnName)) {
            throw new ConstraintViolationException(String.format(ElementConstraintColumnExists.ERROR_MESSAGE, columnName));
        }
    }
}
