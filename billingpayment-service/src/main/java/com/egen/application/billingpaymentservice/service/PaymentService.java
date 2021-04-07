package com.egen.application.billingpaymentservice.service;

import com.egen.application.billingpaymentservice.model.Paymentetails;

import java.util.List;

public interface PaymentService {

    String save(List<Paymentetails> details);

}
