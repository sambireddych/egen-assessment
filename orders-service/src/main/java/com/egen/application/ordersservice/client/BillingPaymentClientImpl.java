package com.egen.application.ordersservice.client;


import com.egen.application.ordersservice.client.models.BillingModel;
import com.egen.application.ordersservice.client.models.PaymentModel;
import com.egen.application.ordersservice.model.Address;
import com.egen.application.ordersservice.model.PaymentDetails;
import com.fasterxml.jackson.databind.JsonNode;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BillingPaymentClientImpl implements BillingPaymentClient {

    private RestTemplate restTemplate;
    private BillingPaymentClientConfig config;

    public BillingPaymentClientImpl(RestTemplateBuilder restTemplateBuilder, BillingPaymentClientConfig config) {
        this.restTemplate = restTemplateBuilder.build();
        this.config = config;
    }

    @Override

    @Retryable(maxAttempts = 3,
            backoff = @Backoff(delay = 100, maxDelay = 2000, random = true))
    @HystrixCommand(groupKey = "billing_payment-service",
            commandKey = "save billing and payment details",
            fallbackMethod = "fallbackMethod")
    public String saveBillingPayment(BillingPayment billingPayment, String orderId, String customerId) throws Exception {
        HttpEntity<BillingModel> billingAddress = new HttpEntity<>(billingMapper(billingPayment.getBillingAddress(), orderId, customerId));
        log.debug("Billing address save internal api called");
        ResponseEntity<Void> billingAdd = restTemplate.exchange
                (config.getUrl() + "/billing/" + orderId + "/address/save", HttpMethod.POST, billingAddress, new ParameterizedTypeReference<Void>() {
                });
        handleClientResponse(billingAdd);


        try {
            HttpEntity<List<PaymentModel>> listHttpEntity = new HttpEntity<>(billingPayment.getPaymentDetails()
                    .stream()
                    .map(paymentDetails -> paymentMapper(paymentDetails, orderId, customerId))
                    .collect(Collectors.toList()));
            log.debug("Billing address save internal api called");
            ResponseEntity<JsonNode> result = restTemplate.exchange
                    (config.getUrl() + "/billing/" + orderId + "/paymentredirect", HttpMethod.POST, listHttpEntity, JsonNode.class);
            handleClientResponse(result);
            return result.getBody().asText();

        } catch (Exception e) {
            log.error("Error occurred in billing payment details API: "+e.getMessage());
        }
        return null;
    }

    private BillingModel billingMapper(Address model, String orderId, String customerId) {
        BillingModel billingModel = new BillingModel();
        billingModel.setOrderId(orderId);
        billingModel.setCustomerId(customerId);
        billingModel.setBillingCity(model.getShipCity());
        billingModel.setBillingAddressLine1(model.getAddressLine1());
        billingModel.setBillingAddressLine2(model.getAddressLine2());
        billingModel.setZip(model.getZip());
        billingModel.setBillingCountry(model.getShipCountry());
        billingModel.setBillingState(model.getShipState());
        return billingModel;

    }

    private PaymentModel paymentMapper(PaymentDetails details, String orderId, String customreId) {
        PaymentModel model = new PaymentModel();
        model.setOrderId(orderId);
        model.setCustomerId(customreId);
        model.setCardType(details.getCardType());
        model.setCardNumber(details.getCardNumber());
        model.setCvv(details.getCvv());
        model.setExpires(details.getExpires());
        model.setNameOnCard(details.getNameOnCard());
        return model;
    }

    private void handleClientResponse(ResponseEntity<?> clientResponse) throws Exception {

        if (clientResponse == null) {
            log.error("response entity is null, down stream service error");
            throw new Exception("Down stream service error");
        }

        if (!clientResponse.getStatusCode().is2xxSuccessful()) {
            log.error("Failure, HttpStatusCode: {}", clientResponse.getStatusCodeValue());
            throw new Exception("Down stream service error");
        }

        log.debug("internal-api call Success, returning response : {}",clientResponse);

    }

}
