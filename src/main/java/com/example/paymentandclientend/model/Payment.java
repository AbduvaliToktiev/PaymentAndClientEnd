package com.example.paymentandclientend.model;

import com.example.paymentandclientend.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment {
    private Integer id;
    private Integer clientId;
    private Integer amount;
    private Date createdDate;
    private PaymentStatus paymentStatus;
    private String msisdn;
}
