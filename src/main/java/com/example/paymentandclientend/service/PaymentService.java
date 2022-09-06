package com.example.paymentandclientend.service;


import com.example.paymentandclientend.dao.ClientRepository;
import com.example.paymentandclientend.dao.PaymentRepository;
import com.example.paymentandclientend.model.Client;
import com.example.paymentandclientend.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientRepository clientRepository;

    public boolean payDebit(Payment payment) {
        Client client = clientRepository.getClientByMsisdn(payment.getMsisdn());
        if (client != null) {
            clientRepository.updateClientBalanceDebit(client.getId(), payment.getAmount());
            paymentRepository.pay(payment);
            return true;
        }
        return false;
    }

    public boolean payCredit(Payment payment) {
        Client client = clientRepository.getClientByMsisdn(payment.getMsisdn());
        if (client != null && client.getBalance() >= payment.getAmount()) {
            clientRepository.updateClientBalanceCredit(client.getId(), payment.getAmount());
            payment.setClientId(client.getId());
            paymentRepository.pay(payment);
            try {
                FileWriter fileWriter = new FileWriter("temp.txt");
                fileWriter.write("MSISDN: " + payment.getMsisdn() + "\n" +
                        "AMOUNT: " + payment.getAmount() + "\n" +
                        "CREATED DATE: " + new Date());
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
            return true;
        }
        return false;
    }
}

