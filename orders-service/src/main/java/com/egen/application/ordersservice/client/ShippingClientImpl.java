package com.egen.application.ordersservice.client;

import com.egen.application.ordersservice.client.models.ShippingModel;
import com.egen.application.ordersservice.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Component
public class ShippingClientImpl implements ShippingClient {

    private RestTemplate restTemplate;
    private ShippingClientConfig config;
    private ObjectMapper objectMapper;

    public ShippingClientImpl(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper, ShippingClientConfig config) {
        this.restTemplate = restTemplateBuilder.build();
        this.config = config;
        this.objectMapper = objectMapper;
    }


    @Retryable(maxAttempts = 3,
            backoff = @Backoff(delay = 100, maxDelay = 2000, random = true))
    @HystrixCommand(groupKey = "getTaxAmount",
            commandKey = "gettax",
            fallbackMethod = "getTaxfallbackMethod")
    @Override
    public BigDecimal getTax(String zip) {
        String url = config.getUrl() + "shipping?zip=" + zip;
        ResponseEntity<BigDecimal> model = restTemplate.getForEntity(url, BigDecimal.class);
        return model.getBody();
    }

    @Retryable(maxAttempts = 3,
            backoff = @Backoff(delay = 100, maxDelay = 2000, random = true))
    @HystrixCommand(groupKey = "post shipping address",
            commandKey = "save shipping address",
            fallbackMethod = "postShippingAddress")
    @Override
    public ShippingModel save(Address model, String orderId, String customerId) throws Exception {
        String url = config.getUrl() + "shipping/" + orderId + "/newShippingAd/";
        ShippingModel shippingModel = shippingAdMapper(model, customerId);
        HttpEntity<ShippingModel> shippingAddress = new HttpEntity<>(shippingModel);
        ResponseEntity<Void> exchange = restTemplate.exchange(url, HttpMethod.POST, shippingAddress, new ParameterizedTypeReference<Void>() {
        });
        handleClientResponse(exchange);
        return null;
    }

    private ShippingModel shippingAdMapper(Address model, String customerId) {
        ShippingModel model1 = new ShippingModel();
        model1.setShippingCity(model.getShipCity());
        model1.setCustomerId(customerId);
        model1.setShippingAddressLine1(model.getAddressLine1());
        model1.setShippingAddressLine2(model.getAddressLine2());
        model1.setZip(model.getZip());
        model1.setShippingCountry(model.getShipCountry());
        model1.setShippingState(model.getShipState());
        return model1;
    }

    private void handleClientResponse(ResponseEntity<Void> clientResponse) throws Exception {

        if (clientResponse == null) {
            log.error("Shipping-Service response entity is null, down stream service error");
            throw new Exception("Down stream service error");
        }

        if (!clientResponse.getStatusCode().is2xxSuccessful()) {
            log.error("Shipping-Service Call Failure, HttpStatusCode: {}", clientResponse.getStatusCodeValue());
            throw new Exception("Down stream service error");
        }

        log.debug("Shipping-Service Success, returning response : {}",clientResponse);
        System.out.println("Shipping-Service Success, returning response : " + clientResponse);

    }

    private BigDecimal getTaxfallbackMethod(String zip) {
        log.error("Shipping Service is down, fall back method");
        return new BigDecimal("11.98");
    }

    private ShippingModel postShippingAddress(Address Model, String orderId) {
        log.error("Shipping Service is down, fall back method");
        return new ShippingModel();
    }

}
