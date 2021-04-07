package com.egen.application.billingpaymentservice.service.impl;

import com.egen.application.billingpaymentservice.model.Paymentetails;
import com.egen.application.billingpaymentservice.repo.PaymentRepo;
import com.egen.application.billingpaymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepo repo;


    //hash the entire table -- TODO
    @Transactional
    @Override
    public String save(List<Paymentetails> details) {

        details.stream().map(paymentetails -> repo.saveAndFlush(paymentetails)).collect(Collectors.toList());

        return details.get(0).getPaymentConfirmationNumber();
    }
}
