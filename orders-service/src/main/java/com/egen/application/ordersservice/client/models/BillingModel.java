package com.egen.application.ordersservice.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonSerialize
public class BillingModel {


    @JsonProperty
    private String OrderId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private String billingAddressLine1;
    @JsonProperty
    private String billingAddressLine2;
    @JsonProperty
    private String billingCity;
    @JsonProperty
    private String billingState;
    @JsonProperty
    private String billingCountry;
    @JsonProperty
    private String zip;

}
