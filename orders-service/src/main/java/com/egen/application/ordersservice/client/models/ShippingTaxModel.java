package com.egen.application.ordersservice.client.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShippingTaxModel {
    private BigDecimal tax;
}
