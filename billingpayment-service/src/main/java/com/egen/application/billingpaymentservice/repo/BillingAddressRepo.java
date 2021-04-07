package com.egen.application.billingpaymentservice.repo;

import com.egen.application.billingpaymentservice.model.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepo extends JpaRepository<BillingAddress, Long> {
}
