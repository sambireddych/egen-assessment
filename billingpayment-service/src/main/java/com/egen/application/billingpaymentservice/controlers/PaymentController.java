package com.egen.application.billingpaymentservice.controlers;


import com.egen.application.billingpaymentservice.model.BillingAddress;
import com.egen.application.billingpaymentservice.model.Paymentetails;
import com.egen.application.billingpaymentservice.service.BillingAddressService;
import com.egen.application.billingpaymentservice.service.PaymentService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService service;
    private final BillingAddressService addressService;
    private ObjectMapper objectMapper;

    @PostMapping("billing/{orderId}/paymentredirect")
    private ResponseEntity<?> save(@RequestBody List<Paymentetails> details, @PathVariable String orderId) throws Exception {
        String paymentConfirmation = service.save(details);
        JsonNode json = objectMapper.convertValue(paymentConfirmation, JsonNode.class);
        return new ResponseEntity<>(json, HttpStatus.ACCEPTED);
    }

    @PostMapping("billing/{orderId}/address/save")
    public ResponseEntity<Void> saveBilling(@RequestBody BillingAddress address, @PathVariable String orderId){
        addressService.saveBillingAddress(address);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("billingaddress/update/{id}")
    public ResponseEntity<?> updateBilling(BillingAddress address) throws Exception {
        return new ResponseEntity<>(addressService.updateBillingAddress(address), HttpStatus.OK);
    }

    @DeleteMapping("billingaddress/update/{id}")
    public ResponseEntity<?> deleteBilling(BillingAddress address){
        return new ResponseEntity<>(addressService.deleteBillingAddress(address), HttpStatus.OK);
    }
}
