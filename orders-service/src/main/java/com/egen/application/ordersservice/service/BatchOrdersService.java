package com.egen.application.ordersservice.service;

import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.model.OrderSummary;

import java.util.List;

public interface BatchOrdersService {

    List<OrderSummary> createBatchOrders(List<Order> order);

    List<Order> updateBatchOrders(List<Order> order);

    void deleteAllinBatch(List<Order> orders);
}
