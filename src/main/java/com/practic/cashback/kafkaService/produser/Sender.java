package com.practic.cashback.kafkaService.produser;

import com.practic.cashback.model.Cashback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;




public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Cashback> kafkaTemplate;

    public void send(Cashback cashback) {
        LOGGER.info("sending car='{}'", cashback.toString());
        kafkaTemplate.send(jsonTopic, cashback);
    }
}
