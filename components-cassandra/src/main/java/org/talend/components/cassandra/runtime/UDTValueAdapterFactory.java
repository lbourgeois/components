package org.talend.components.cassandra.runtime;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import org.talend.daikon.avro.IndexedRecordAdapterFactory;
import org.talend.daikon.avro.util.AvroUtils;

/**
 * Creates an {@link IndexedRecordAdapterFactory} that knows how to interpret Cassandra {@link UDTValue} objects.
 */
public class UDTValueAdapterFactory extends CassandraBaseAdapterFactory<UDTValue, UDTValue, UserType> {

    @Override
    public Class<UDTValue> getDatumClass() {
        return UDTValue.class;
    }

    @Override
    protected void setContainerTypeFromInstance(UDTValue udt) {
        setContainerType(udt.getType());
    }

    @Override
    protected DataType getFieldType(int i) {
        return getContainerType().getFieldType(AvroUtils.unwrapIfNullable(getSchema()).getFields().get(i).name());
    }

    @Override
    protected UDTValue createOrGetInstance() {
        return getContainerType().newValue();
    }

}