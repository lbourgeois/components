package org.talend.components.common;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.IndexedRecord;

/**
 * Helper class for IndexedRecord manipulation //TODO may be moved to Daikon
 */
public class IndexedRecordUtil {

    /**
     * Retrieve a field from on indexedRecord.
     * 
     * @param fieldPath the field name. Can be a path for hierarchical element
     * @param record an Indexed record
     * @return the Object matching to the fieldName if it was found, null otherwise
     */
    public static Object getField(String fieldPath, IndexedRecord record) {
        // TODO current implementation will only extract one element, but
        // further implementation may
        String[] path = fieldPath.split("\\.");
        Schema schema = record.getSchema();

        for (Integer i = 0; i < path.length; i++) {
            if (schema.getField(path[i]) == null) {
                return null;
            }
            // The column was existing on the input record, we forward it to the
            // output record.
            Object inputValue = record.get(schema.getField(path[i]).pos());

            // The current column can be a Record (an hierarchical sub-object)
            // or directly a value.
            if (inputValue instanceof Record) {
                // If we are on a record, we need to recursively do the process
                record = (IndexedRecord) inputValue;

                // The sub-schema at this level is a union of "empty" and a
                // record, so we need to get the true sub-schema
                if (schema.getField(path[i]).schema().getType().equals(Type.RECORD)) {
                    schema = schema.getField(path[i]).schema();
                } else if (schema.getField(path[i]).schema().getType().equals(Type.UNION)) {
                    for (Schema childSchema : schema.getField(path[i]).schema().getTypes()) {
                        if (childSchema.getType().equals(Type.RECORD)) {
                            schema = childSchema;
                            break;
                        }
                    }
                }

            } else {
                // if we are on a object, then this is or the expected value of an error.
                if (i == path.length - 1) {
                    return inputValue;
                } else {
                    // No need to go further, return an empty list
                    return null;
                }
            }
        }
        // field not found, return an empty list
        return null;
    }

    /**
     * Check if a record has a specific column
     * 
     * @param record
     * @param column
     */
    public static boolean hasColumn(IndexedRecord record, String column) {
        String[] path = column.split("\\.");
        Schema schema = record.getSchema();

        for (Integer i = 0; i < path.length; i++) {
            if (schema.getField(path[i]) == null) {
                return false;
            }
        }

        return true;
    }

}
