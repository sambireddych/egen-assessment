package com.egen.application.billingpaymentservice.events;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class PaymentConfirmationEvent {
    private final String orderId;
}