package com.egen.application.ordersservice.client;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "billing-payment-service")
@Getter
@Setter
public class BillingPaymentClientConfig {
    private String url;
}
