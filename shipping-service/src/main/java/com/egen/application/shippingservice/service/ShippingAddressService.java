package com.egen.application.shippingservice.service;

import com.egen.application.shippingservice.model.ShippingAddress;

public interface ShippingAddressService {

    void saveShippingAddress(ShippingAddress address) throws Exception;
    ShippingAddress fingByAddressId(Long id);
    ShippingAddress updateBillingAddress(ShippingAddress address) throws Exception;
    void deleteBillingAddress(ShippingAddress address);


}
