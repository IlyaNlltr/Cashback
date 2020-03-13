package com.practic.cashback.kafka.produser;

import com.practic.cashback.dto.CashbackDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class Sender {

    @Value("${spring.kafka.topic.json}")
    private String jsonSending;

    @Autowired
    private KafkaTemplate<String, CashbackDTO> kafkaTemplate;

    public void sendMessege(CashbackDTO cashback) {
        ListenableFuture<SendResult<String, CashbackDTO>> future = kafkaTemplate
                .send(jsonSending, cashback);
        future.addCallback(new ListenableFutureCallback<SendResult<String, CashbackDTO>>() {

            @Override
            public void onSuccess(SendResult<String, CashbackDTO> result) {
                log.info("Massage send successed:{}{}", cashback, jsonSending);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Massage failure:{}{}", cashback, jsonSending);
            }
        });
    }
}
