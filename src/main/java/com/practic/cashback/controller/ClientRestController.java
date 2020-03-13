package com.practic.cashback.controller;

import com.practic.cashback.dao.ClientRepository;
import com.practic.cashback.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientRestController {

    private final ClientRepository clientRepository;

    @PostMapping("/cashback/client")// добавить нового клиента
    public ResponseEntity<Client> createCustomer(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
}
