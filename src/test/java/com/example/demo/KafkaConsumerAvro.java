package com.example.demo;

import com.example.demo.schema.TestEvent1;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumerAvro {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerAvro.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;
    private TestEvent1 data;


    @KafkaListener(topics = "${test.topicavro}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        payload = consumerRecord.toString();
        data = (TestEvent1) consumerRecord.value();
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    public TestEvent1 getData() {
        return data;
    }
}
