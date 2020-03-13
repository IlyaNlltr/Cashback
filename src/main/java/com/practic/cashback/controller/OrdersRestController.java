package com.practic.cashback.controller;

import com.practic.cashback.dao.OrdersRepository;
import com.practic.cashback.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrdersRestController {

    private final OrdersRepository ordersRepository;

    @PostMapping("/cashback/orders") // добавить новый заказ
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        Orders savedOrders = ordersRepository.save(orders);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }
}
