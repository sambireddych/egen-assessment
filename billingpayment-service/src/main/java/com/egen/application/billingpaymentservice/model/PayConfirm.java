package com.egen.application.billingpaymentservice.model;

import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = PaymentConfirmationNumberGenerator.class)
@Retention(RetentionPolicy.RUNTIME )
public @interface PayConfirm {
}
