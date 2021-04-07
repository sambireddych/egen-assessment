package com.egen.application.ordersservice.client;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "shipping-management-service")
@Getter
@Setter
public class ShippingClientConfig {
    String url;
}
