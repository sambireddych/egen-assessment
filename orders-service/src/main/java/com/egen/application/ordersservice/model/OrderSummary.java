package com.egen.application.ordersservice.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderSummary {

    private String OrderId;
    private BigDecimal orderTotal;
    private String orderStatus;
    private String paymentConfirmationNumber;

    public OrderSummary(){

    }
}
