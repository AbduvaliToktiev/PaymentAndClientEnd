package com.example.paymentandclientend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private Integer id;
    private String fio;
    private Integer balance;
    private Boolean active;
    private String msisdn;
}
