package com.practic.cashback.controller;

import com.practic.cashback.Status.StatusEnum;
import com.practic.cashback.dao.CashbackRepository;
import com.practic.cashback.dao.ClientRepository;
import com.practic.cashback.dao.OrdersRepository;
import com.practic.cashback.dao.ProcessResRepository;
import com.practic.cashback.dto.CashbackDTO;
import com.practic.cashback.dto.ProcessResDTO;
import com.practic.cashback.kafka.produser.Sender;
import com.practic.cashback.model.Cashback;
import com.practic.cashback.model.ProcessRes;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CashbackRestController {

    private final Sender sender;
    private final ClientRepository clientRepository;
    private final OrdersRepository ordersRepository;
    private final ProcessResRepository processResRepository;
    private final CashbackRepository cashbackRepository;

    @GetMapping("/cashback/calc")
    public ResponseEntity<ProcessResDTO> getCashback(@RequestParam(value = "user_id") Long client_id,
                                                     @RequestParam(value = "orders_id") Long orders_id) {
        Cashback cashback = new Cashback();
        ProcessRes processRes = new ProcessRes();
        processRes.setStatus(StatusEnum.IN_PROGRESS);
        processResRepository.save(processRes);
        cashback.setClient(clientRepository.findById(client_id)
                .get());
        cashback.setOrders(ordersRepository.findById(orders_id)
                .get());
        cashback.setProcessRes(processRes);
        cashbackRepository.save(cashback);
        ModelMapper modelMapper = new ModelMapper();
        ProcessResDTO processResDTO = modelMapper.map(processResRepository
                .findById(cashback.getProcessRes().getId())
                .get(), ProcessResDTO.class);
        CashbackDTO cashbackDTO = modelMapper.map(cashback, CashbackDTO.class);
        sender.sendMessege(cashbackDTO);
        return new ResponseEntity<>(processResDTO, HttpStatus.OK);
    }

    @GetMapping("/cashback/check")
    public ResponseEntity<ProcessRes> getStatus(@RequestParam(value = "executionId") Long resultId) {
        return new ResponseEntity<>(processResRepository.findById(resultId).get(), HttpStatus.OK);
    }
}


