package com.practic.cashback.controller;

import com.practic.cashback.dao.ClientRepository;
import com.practic.cashback.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Аннотация, которая превращает класс в контроллер-бин (дает возможность обращаться к методам по http)
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientRestController {
    private final ClientRepository clientRepository;


    @GetMapping // получить список всех челиков
    public ResponseEntity<Iterable<Client>> getClientList() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}") // получить челика по ID
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}") // удалить одиного челика по ID
    public ResponseEntity removeClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping // сохранить нового челика
    public ResponseEntity<Client> createCustomer(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("{id}") // обновить данные челиков
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.OK);
    }
}
