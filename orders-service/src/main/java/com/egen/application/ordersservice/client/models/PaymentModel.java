package com.egen.application.ordersservice.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonSerialize
public class PaymentModel {


    @JsonProperty
    private String orderId;
    @JsonProperty
    private String cardType;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private String nameOnCard;
    @JsonProperty
    private String cardNumber;
    @JsonProperty
    @NotEmpty
    private Integer cvv;
    @JsonProperty
    private String expires;
}
