package com.egen.application.billingpaymentservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BillingAddress")
@Getter
@Setter
public class BillingAddress {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "address_line1")
    private String billingAddressLine1;
    @Column(name = "address_line2")
    private String billingAddressLine2;
    @Column(name = "city")
    private String billingCity;
    @Column(name = "state")
    private String billingState;
    @Column(name = "country")
    private String billingCountry;
    @Column(name = "zip")
    private String zip;
}
