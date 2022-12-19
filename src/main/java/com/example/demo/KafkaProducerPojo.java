package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerPojo {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerPojo.class);


    private final KafkaTemplate<String, Greeting> kafkaTemplate;

    public KafkaProducerPojo(KafkaTemplate<String, Greeting> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Greeting payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, "clave", payload);
    }
}
