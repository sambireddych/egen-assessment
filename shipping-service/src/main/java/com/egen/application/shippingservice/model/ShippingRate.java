package com.egen.application.shippingservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name ="ShippingRates")
public class ShippingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ShippingRateID")
    private long id;

    @Column(name = "zip")
    private String zip;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;
    @Version
    private long Version;
}
