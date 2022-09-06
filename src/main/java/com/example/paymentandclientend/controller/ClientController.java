package com.example.paymentandclientend.controller;

import com.example.paymentandclientend.model.Client;
import com.example.paymentandclientend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/client")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/save-client")
    public String saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return "SAVE CLIENT!";
    }
}