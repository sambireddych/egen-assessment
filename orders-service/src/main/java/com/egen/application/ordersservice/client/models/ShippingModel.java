package com.egen.application.ordersservice.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;


@JsonSerialize
@Getter
@Setter
public class ShippingModel {


    @JsonProperty
    private String customerId;
    @JsonProperty
    private String shippingAddressLine1;
    @JsonProperty
    private String shippingAddressLine2;
    @JsonProperty
    private String shippingCity;
    @JsonProperty
    private String shippingState;
    @JsonProperty
    private String shippingCountry;
    @JsonProperty
    private String zip;


}
