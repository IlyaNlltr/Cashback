package com.practic.cashback.controller;

import com.practic.cashback.dao.OrdersRepository;
import com.practic.cashback.model.Client;
import com.practic.cashback.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Аннотация, которая превращает класс в контроллер-бин (дает возможность обращаться к методам по http)
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrdersRepository ordersRepository;


    @GetMapping // получить список всех челиков
    public ResponseEntity<Iterable<Orders>> getOrdersList() {
        return new ResponseEntity<>(ordersRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("{id}") // получить один заказ по ID
    public ResponseEntity<Orders> getOrders(@PathVariable Long id) {
        return new ResponseEntity<>(ordersRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}") // удалить один заказ по ID
    public ResponseEntity removeOrders(@PathVariable Long id) {
        ordersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping // сохранить новый заказ
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        Orders savedOrders = ordersRepository.save(orders);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @PutMapping("{id}") // обновить данные заказов
    public ResponseEntity<Orders> updateOrders(@PathVariable Long id, @RequestBody Orders orders) {
        orders.setId(id);
        Orders savedOrders = ordersRepository.save(orders);
        return new ResponseEntity<>(savedOrders, HttpStatus.OK);
    }
}
