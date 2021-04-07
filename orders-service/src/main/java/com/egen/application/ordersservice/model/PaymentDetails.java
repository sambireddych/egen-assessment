package com.egen.application.ordersservice.model;//package org.egen.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDetails {

    private String cardType;
    private String nameOnCard;
    private String cardNumber;
    private Integer cvv;
    private String expires;

}
