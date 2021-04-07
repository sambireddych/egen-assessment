package com.egen.application.shippingservice.service.Impl;

import com.egen.application.shippingservice.model.ShippingAddress;
import com.egen.application.shippingservice.repo.ShippingAddressRepo;
import com.egen.application.shippingservice.service.ShippingAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {


    private ShippingAddressRepo repo;

    @Override
    public void saveShippingAddress(ShippingAddress address) throws Exception {
        try{
            repo.saveAndFlush(address);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ShippingAddress fingByAddressId(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public ShippingAddress updateBillingAddress(ShippingAddress address) throws Exception {
        ShippingAddress getAddress = this.fingByAddressId(address.getId());
        if (getAddress == null){
            throw new Exception("Not Found");
        }
        ShippingAddress updated = repo.save(getAddress);
        return updated;
    }

    @Override
    public void deleteBillingAddress(ShippingAddress address) {
        repo.delete(address);
    }
}
