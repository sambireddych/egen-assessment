package com.egen.application.ordersservice.client;


public interface BillingPaymentClient {
    String saveBillingPayment(BillingPayment billingPayment, String orderId, String customerId) throws Exception;
}
