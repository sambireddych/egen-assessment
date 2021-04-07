package com.egen.application.shippingservice.controllers;


import com.egen.application.shippingservice.model.ShippingAddress;
import com.egen.application.shippingservice.model.ShippingRate;
import com.egen.application.shippingservice.service.ShippingAddressService;
import com.egen.application.shippingservice.service.ShippingRatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shipping")
@RequiredArgsConstructor
public class ShippingRatesController {

    private final ShippingRatesService service;
    private final ShippingAddressService shippingAddressService;
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<ShippingRate>> get() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = "zip")
    private ResponseEntity<?> getByZip(@RequestParam String zip) {
        BigDecimal val = service.calcuateRate(zip);
        System.out.println(val);
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {

        return new ResponseEntity<>(service.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/newShippingRates")
    public ResponseEntity<?> save(@RequestBody ShippingRate shippingRates) {
        long id = shippingRates.getId();
        Optional<ShippingRate> exist = service.getById(id);
        if (exist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        ShippingRate savedShippingRates = service.save(shippingRates);
        return new ResponseEntity<>(savedShippingRates, HttpStatus.OK);
    }
    @PostMapping("/{orderId}/newShippingAd")
    public ResponseEntity<Void> saveShippingAddress(@RequestBody ShippingAddress shippingAddress, @PathVariable String orderId) throws Exception {

        shippingAddressService.saveShippingAddress(shippingAddress);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) throws Exception {
        Optional<ShippingRate> exist = service.getById(id);
        if (!exist.isPresent()) {
            throw new Exception("Not found");
        }
        service.delete(exist.get());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateFlatRate(@PathVariable long id, @RequestBody ShippingRate shippingRates) {
        Optional<ShippingRate> exist = service.getById(id);
        if (!exist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exist.get().setTaxAmount(shippingRates.getTaxAmount());
        exist.get().setZip(shippingRates.getZip());
        return new ResponseEntity<>(service.save(exist.get()), HttpStatus.OK);
    }
}
