package com.egen.application.shippingservice.service;

import com.egen.application.shippingservice.model.ShippingRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ShippingRatesService {

    List<ShippingRate> getAll();
    Optional<ShippingRate> getById(long id);
    ShippingRate save(ShippingRate shippingRates);
    void delete(ShippingRate shippingRates);
    BigDecimal calcuateRate(String zip);
}
