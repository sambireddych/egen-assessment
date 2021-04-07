package com.egen.application.shippingservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "ShippingAddress")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "address_line1")
    @NotEmpty
    private String shippingAddressLine1;
    @Column(name = "address_line2")
    @NotEmpty
    private String shippingAddressLine2;
    @Column(name = "city")
    @NotEmpty
    private String shippingCity;
    @Column(name = "state")
    @NotEmpty
    private String shippingState;
    @Column(name = "country")
    @NotEmpty
    private String shippingCountry;
    @Column(name = "zip")
    @NotEmpty
    private String zip;
}
