package com.egen.application.shippingservice.repo;


import com.egen.application.shippingservice.model.ShippingRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRatesRepo extends JpaRepository<ShippingRate, Long> {

    ShippingRate findByZip(String zip);
}
