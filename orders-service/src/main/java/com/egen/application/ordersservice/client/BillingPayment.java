package com.egen.application.ordersservice.client;

import com.egen.application.ordersservice.model.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class BillingPayment {

    private Address billingAddress;
    private List<PaymentDetails> paymentDetails;

}
