package com.egen.application.ordersservice.repo;

import com.egen.application.ordersservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderManagerRepo extends JpaRepository<Order, String> {

    List<Order> findByOrderCustomerId(String customerId);
}
