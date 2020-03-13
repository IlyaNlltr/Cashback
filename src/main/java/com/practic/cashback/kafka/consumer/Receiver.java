package com.practic.cashback.kafka.consumer;

import com.practic.cashback.Status.StatusEnum;
import com.practic.cashback.dao.CashbackRepository;
import com.practic.cashback.dao.ProcessResRepository;
import com.practic.cashback.dto.CashbackDTO;
import com.practic.cashback.model.Cashback;
import com.practic.cashback.model.ProcessRes;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class Receiver {

    private final ProcessResRepository processResRepository;
    private final CashbackRepository cashbackRepository;

    @KafkaListener(topics = "${spring.kafka.topic.json}")
    public void receive(CashbackDTO cashbackDTO) {

        ModelMapper modelMaper = new ModelMapper();
        Cashback cashback = modelMaper.map(cashbackDTO, Cashback.class);
        cashback.setMoney(cashback.getOrders().getSumm() * 0.02);
        cashbackRepository.save(cashback);
        ProcessRes processRes = cashback.getProcessRes();
        if (cashback.getMoney() > 0) {
            processRes.setStatus(StatusEnum.SUCCSESS);
            processRes.setMoney(cashback.getMoney());
        } else {
            processRes.setStatus(StatusEnum.ERROR);
        }
        processResRepository.save(processRes);
    }
}
