package com.example.demo;

import com.example.demo.schema.TestEvent1;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.Schema;

import java.io.IOException;

/**
 * This code is not thread safe and should not be used in production environment
 */
public class CustomKafkaAvroDeserializer extends KafkaAvroDeserializer {
    @Override
    public Object deserialize(String topic, byte[] bytes) {
        this.schemaRegistry = getMockClient(TestEvent1.SCHEMA$);
        return super.deserialize(topic, bytes);
    }

    private static SchemaRegistryClient getMockClient(final Schema schema$) {
        MockSchemaRegistryClient mock = new MockSchemaRegistryClient();
        try {
            mock.register("", schema$);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return mock;
    }
}
