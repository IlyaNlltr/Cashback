package com.practic.cashback.kafkaService.consumer;

import java.util.concurrent.CountDownLatch;

import com.practic.cashback.dao.CashbackRepository;
import com.practic.cashback.model.Cashback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    CashbackRepository cashbackRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.json}")
    public void receive(Cashback cashback) {
        cashbackRepository.save(cashback);
        cashback.setMoney(cashback.getOrders().getSumm() * 0.02);
        cashbackRepository.save(cashback);
        LOGGER.info("received cashback='{}'", cashback.toString());
        latch.countDown();
    }
}
