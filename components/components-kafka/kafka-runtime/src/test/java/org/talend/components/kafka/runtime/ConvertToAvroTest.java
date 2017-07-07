package org.talend.components.kafka.runtime;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.SchemaCompatibility;
import org.apache.avro.SchemaCompatibility.SchemaPairCompatibility;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ConvertToAvroTest {

    private static final Schema inputSimpleSchema1 = SchemaBuilder.record("inputRow") //
            .fields() //
            .name("a").type().optional().stringType() //
            .name("b").type().optional().stringType() //
            .name("c").type().optional().stringType() //
            .endRecord();

    private static final Schema inputSimpleSchema2 = SchemaBuilder.record("inputRow") //
            .fields() //
            .name("d").type().optional().stringType() //
            .name("e").type().optional().stringType() //
            .name("f").type().optional().stringType() //
            .endRecord();

    private static final GenericRecord inputSimpleRecord1 = new GenericRecordBuilder(inputSimpleSchema1) //
            .set("a", "aaa") //
            .set("b", "BBB") //
            .set("c", "Ccc") //
            .build();

    private static final GenericRecord inputSimpleRecord2 = new GenericRecordBuilder(inputSimpleSchema2) //
            .set("d", "ddd") //
            .set("e", "eee") //
            .set("f", "fff") //
            .build();

    // @Test
    // public void processBundleTest() throws Exception {
    //
    // BinaryEncoder encoder = null;
    //
    // DatumWriter<GenericRecord> writer1 = new GenericDatumWriter<GenericRecord>(inputSimpleSchema1);
    // ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    // encoder = EncoderFactory.get().binaryEncoder(out1, null);
    // writer1.write(inputSimpleRecord1, encoder);
    // encoder.flush();
    // out1.close();
    //
    // DatumWriter<GenericRecord> writer2 = new GenericDatumWriter<GenericRecord>(inputSimpleSchema2);
    // ByteArrayOutputStream out2 = new ByteArrayOutputStream();
    // encoder = EncoderFactory.get().binaryEncoder(out2, null);
    // writer2.write(inputSimpleRecord2, encoder);
    // encoder.flush();
    // out2.close();
    //
    // String schemaString = inputSimpleSchema1.toString();
    // new KafkaInputProperties("testprop").setDatasetProperties(new
    // KafkaDatasetProperties("testDataSet").main.schema.setValue(inputSimpleSchema1));
    // ConvertToAvro function = new KafkaInputPTransformRuntime.ConvertToAvro(schemaString, true);
    // DoFnTester<byte[], IndexedRecord> fnTester = DoFnTester.of(function);
    //
    // List<IndexedRecord> outputs1 = fnTester.processBundle(out1.toByteArray());
    // Assert.assertEquals(outputs1.size(), 1);
    //
    // List<IndexedRecord> outputs2 = fnTester.processBundle(out2.toByteArray());
    // Assert.assertEquals(outputs2.size(), 1);
    // }

    @Test
    public void schemaCompatibilityTest() {
        SchemaPairCompatibility schemaCompatibility = SchemaCompatibility.checkReaderWriterCompatibility(inputSimpleSchema1,
                inputSimpleSchema2);
        Assert.assertEquals(schemaCompatibility.getType(), SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE);
    }

    @Test
    public void getAvroShemaStr() {
        Schema inputSimpleSchema = SchemaBuilder.record("inputRow") //
                .fields() //
                .name("a").type().optional().stringType() //
                .name("b").type().optional().stringType() //
                .name("c").type().optional().stringType() //
                .endRecord();
        String schemaString = inputSimpleSchema.toString();
    }

}
