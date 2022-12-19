package com.example.demo;

import com.example.demo.schema.TestEvent1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerAvro {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerAvro.class);


    private final KafkaTemplate<String, TestEvent1> kafkaTemplate;

    public KafkaProducerAvro(KafkaTemplate<String, TestEvent1> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, TestEvent1 payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, "clave", payload);
    }
}
