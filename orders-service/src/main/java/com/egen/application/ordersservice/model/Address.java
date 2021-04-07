package com.egen.application.ordersservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Address implements Serializable {

    @NotEmpty
    private String addressLine1;
    private String addressLine2;
    @NotEmpty
    private String shipCity;
    @NotEmpty
    private String shipState;
    @NotEmpty
    private String shipCountry;
    @NotEmpty
    private String zip;

}
