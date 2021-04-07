package com.egen.application.billingpaymentservice.service.impl;

import com.egen.application.billingpaymentservice.model.BillingAddress;
import com.egen.application.billingpaymentservice.repo.BillingAddressRepo;
import com.egen.application.billingpaymentservice.service.BillingAddressService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BillingAddressServiceImpl implements BillingAddressService {

    private BillingAddressRepo repo;
    @Override
    public void saveBillingAddress(BillingAddress address) {
            repo.saveAndFlush(address);

    }

    @Override
    public BillingAddress fingByAddressId(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public BillingAddress updateBillingAddress(BillingAddress address) throws Exception {
        BillingAddress getAddress = this.fingByAddressId(address.getId());
        if (getAddress == null){
            throw new Exception("Not Found");
        }
        BillingAddress updated = repo.saveAndFlush(getAddress);
        return updated;
    }

    @Override
    public String deleteBillingAddress(BillingAddress address) {

        try{
            repo.delete(address);
            return "Successfully delated";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
