package com.example.paymentandclientend.controller;

import com.example.paymentandclientend.model.Payment;
import com.example.paymentandclientend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/pay-debit")
    public String payDebit(@RequestBody Payment payment) {
        boolean result = paymentService.payDebit(payment);
        if (result) return "DEBIT";
        return "FAILED";
    }

    @PostMapping(value = "/pay-credit")
    public String payCredit(@RequestBody Payment payment) {
        boolean result = paymentService.payCredit(payment);
        if (result) return "CREDIT";
        return "FAILED";
    }
}