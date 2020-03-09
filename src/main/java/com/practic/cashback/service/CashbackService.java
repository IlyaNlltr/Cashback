package com.practic.cashback.service;

import com.practic.cashback.dao.ClientRepository;
import com.practic.cashback.dao.OrdersRepository;
import com.practic.cashback.model.Client;
import com.practic.cashback.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CashbackService {

    private final ClientRepository clientRepository;
    private final OrdersRepository ordersRepository;

    public void addNewClient(Client client) {
        clientRepository.save(client);
    }

    public void addNewOrder(Orders orders) {
        ordersRepository.save(orders);
    }


}
