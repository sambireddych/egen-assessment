package com.egen.application.ordersservice.service;

import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.model.OrderSummary;


import java.util.List;
import java.util.Optional;

public interface OrderManagerService {

    List<Order> getAll(String customerId);

    OrderSummary createOrder(Order order) throws Exception;
    Optional<Order> getOrderById(String orderId);

    Void deleteOrderById(String orderId);

    List<Order> getOrderHistory(String customerId);

}

