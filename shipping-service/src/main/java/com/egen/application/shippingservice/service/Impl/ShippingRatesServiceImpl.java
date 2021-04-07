package com.egen.application.shippingservice.service.Impl;


import com.egen.application.shippingservice.model.ShippingRate;
import com.egen.application.shippingservice.repo.ShippingRatesRepo;
import com.egen.application.shippingservice.service.ShippingRatesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ShippingRatesServiceImpl implements ShippingRatesService {

    private ShippingRatesRepo repo;
    @Override
    public List<ShippingRate> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ShippingRate> getById(long id) {
        return Optional.of(repo.findById(id)).get();
    }

    @Override
    public ShippingRate save(ShippingRate shippingRates) {
        log.debug("saved {}", shippingRates);
        return repo.saveAndFlush(shippingRates);

    }

    @Override
    public void delete(ShippingRate shippingRates) {
        repo.delete(shippingRates);
        log.debug(shippingRates +": deleted");
    }

    @Override
    public BigDecimal calcuateRate(String zip) {
        log.debug("getting tax amount based on ZIP code");
        try {
            BigDecimal tax = repo.findByZip(zip).getTaxAmount();
            return tax;
        }catch (NullPointerException np){
            return new BigDecimal("12.67");
        }
    }
}
