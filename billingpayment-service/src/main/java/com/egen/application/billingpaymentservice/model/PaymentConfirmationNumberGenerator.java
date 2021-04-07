package com.egen.application.billingpaymentservice.model;


import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class PaymentConfirmationNumberGenerator implements AnnotationValueGeneration<PayConfirm> {

    private final ValueGenerator<String> generator = ((session, customer) -> RandomStringUtils.random(12, "abcdefghijklmnopqurstuvwxyz"));

    @Override
    public void initialize(PayConfirm annotation, Class<?> propertyType) {

    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}
