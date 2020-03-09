package com.practic.cashback;

import com.practic.cashback.model.Cashback;
import com.practic.cashback.model.Client;
import com.practic.cashback.model.Orders;
import com.practic.cashback.service.CashbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CashbackApplication {

    @Autowired
    private CashbackService cashbackService;

    public static void main(String[] args) {
        SpringApplication.run(CashbackApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        addTestOrders();
    }

    private void addTestOrders() {

        Client client = new Client();
        client.setName("Вася");
        client.setEmail("vasya@gmail.com");
        client.setPhone("+380978425718");

        Orders orders = new Orders();
        orders.setDate("2020-02-10");
        orders.setOrderName("Заказ зубочисток");
        orders.setSumm(254.0);
        orders.setClientOrders(client);

        cashbackService.addNewClient(client);
        cashbackService.addNewOrder(orders);
    }
}

