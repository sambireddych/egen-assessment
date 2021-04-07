package com.egen.application.billingpaymentservice.repo;

import com.egen.application.billingpaymentservice.model.Paymentetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Paymentetails, String> {
}
