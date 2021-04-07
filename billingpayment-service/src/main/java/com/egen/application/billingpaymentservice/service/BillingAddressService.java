package com.egen.application.billingpaymentservice.service;

import com.egen.application.billingpaymentservice.model.BillingAddress;

public interface BillingAddressService {

    void saveBillingAddress(BillingAddress address);
    BillingAddress fingByAddressId(Long id);
    BillingAddress updateBillingAddress(BillingAddress address) throws Exception;
    String deleteBillingAddress(BillingAddress address);
}
