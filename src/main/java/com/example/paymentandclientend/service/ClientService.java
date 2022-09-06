package com.example.paymentandclientend.service;

import com.example.paymentandclientend.dao.ClientRepository;
import com.example.paymentandclientend.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        Client exitClient = clientRepository.getClientByMsisdn(client.getMsisdn());
        if (exitClient != null) {
            clientRepository.blockClientById(exitClient.getId());
        }
        clientRepository.saveClient(client);
    }
}

