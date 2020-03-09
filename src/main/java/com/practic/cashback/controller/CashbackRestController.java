package com.practic.cashback.controller;

import com.practic.cashback.dao.ClientRepository;
import com.practic.cashback.dao.OrdersRepository;
import com.practic.cashback.dao.ProcessResRepository;
import com.practic.cashback.dto.ProcessRes;
import com.practic.cashback.kafkaService.produser.Sender;
import com.practic.cashback.model.AnswearEnum;
import com.practic.cashback.model.Cashback;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class CashbackRestController {
    private final Sender sender;

    @Autowired
    CashbackRestController(Sender sender) {
        this.sender = sender;
    }

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrdersRepository ordersRepository;

    ProcessResRepository processResRepository;


    @GetMapping("/cashback/calc")
    public ResponseEntity<ProcessRes> getCashback(@RequestParam(value = "user_id") Long client_id,
                                                  @RequestParam(value = "orders_id") Long orders_id) {
        Cashback cashback = new Cashback();
        ProcessRes processRes = new ProcessRes();

        cashback.setClient(clientRepository.findById(client_id).get());
        cashback.setOrders(ordersRepository.findById(orders_id).get());
        cashback.setProcessRes(processRes);
        processRes.setStatus(AnswearEnum.IN_PROGRESS);
        processRes.setCashback(cashback);
        this.sender.send(cashback);
        return new ResponseEntity<>(cashback.getProcessRes(), HttpStatus.OK);
    }

    @GetMapping("/cashback/check/{id}")
    public ResponseEntity<ProcessRes> getStatus(@RequestParam(value = "executionid: ") Long resultId) {
        ProcessRes processRes = processResRepository.findById(resultId).get();
        if (processRes.getCashback().getMoney() > 0 & processRes.getCashback().getOrders().getSumm() > 0) {
            processRes.setStatus(AnswearEnum.SUCCSESS);
        } else {
            processRes.setStatus(AnswearEnum.ERROR);
        }
        processResRepository.save(processRes);
        return new ResponseEntity<>(processRes, HttpStatus.OK);
    }
}
