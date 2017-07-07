package org.talend.components.kafka.input;

import org.apache.avro.SchemaCompatibility;
import org.apache.avro.SchemaCompatibility.SchemaPairCompatibility;
import org.apache.avro.generic.IndexedRecord;
import org.talend.components.api.exception.ConstraintViolationException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.ElementConstraintCompatibleSchema;

public class KafkaInputConstraintCompatibleSchema extends ElementConstraintCompatibleSchema {

    @Override
    public void validate(IndexedRecord element, ComponentProperties properties) throws ConstraintViolationException {

        // Check schema compatibility
        SchemaPairCompatibility schemaCompatibility = SchemaCompatibility.checkReaderWriterCompatibility(
                ((KafkaInputProperties) properties).getDatasetProperties().main.schema.getValue(), element.getSchema());
        if (!SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE.equals(schemaCompatibility.getType())) {
            throw new ConstraintViolationException(ElementConstraintCompatibleSchema.ERROR_MESSAGE);
        }

    }
}
