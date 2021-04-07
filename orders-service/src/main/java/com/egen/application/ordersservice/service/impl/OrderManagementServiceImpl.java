package com.egen.application.ordersservice.service.impl;

import com.egen.application.ordersservice.client.BillingPayment;
import com.egen.application.ordersservice.client.BillingPaymentClient;
import com.egen.application.ordersservice.client.ShippingClient;
import com.egen.application.ordersservice.handlers.OrderServiceException;
import com.egen.application.ordersservice.model.Address;
import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.model.OrderItems;
import com.egen.application.ordersservice.model.OrderSummary;
import com.egen.application.ordersservice.model.enums.OrderStatus;
import com.egen.application.ordersservice.repo.OrderManagerRepo;
import com.egen.application.ordersservice.service.OrderManagerService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderManagementServiceImpl implements OrderManagerService {


    private OrderManagerRepo repo;
    private ShippingClient shippingClient;
    private BillingPaymentClient billingPaymentClient;



    @Override
    public List<Order> getAll(String customerId) {
        return repo.findAllById(Collections.singleton(customerId));
    }

    @Transactional
    @Override
    public OrderSummary createOrder(Order order) throws Exception {

        return saveAllOrder(order);

    }

    private void validateOrderAmount(Order order){
        BigDecimal totalAmount = order.getOrderTotal();
        BigDecimal totalOrderItemsAmount = BigDecimal.ZERO;
        for(OrderItems orderDetail : order.getOrder_items()){
            totalOrderItemsAmount = totalOrderItemsAmount.add(
                    orderDetail.getOrderItemUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getOrderItemQuantity())));
        }

        if(!(totalAmount.equals(totalOrderItemsAmount.add(order.getOrderShippingCharges()).add(order.getOrderTax())))){
            log.error("Total Order Amount Mismatched");
            throw new OrderServiceException("Total Amount Mismatched");
        }

    }
    private OrderSummary saveAllOrder(Order order) throws Exception {
        Address billingAddress = order.getBillingAddress();
        Address shippingAddress = order.getShippingAddress();
        BillingPayment billingPayment = new BillingPayment();
        billingPayment.setBillingAddress(billingAddress);
        billingPayment.setPaymentDetails(order.getPaymentDetails());
        order.setOrderStatus(OrderStatus.PLACED);
        Order orderSave = repo.saveAndFlush(order);
        shippingClient.save(shippingAddress, orderSave.getOrderId(), orderSave.getOrderCustomerId());
        order.setOrderTax(shippingClient.getTax(shippingAddress.getZip()));
        String paymentConfirmation = billingPaymentClient.saveBillingPayment(billingPayment, orderSave.getOrderId(), orderSave.getOrderCustomerId());
        validateOrderAmount(order);
        log.debug("Order saved");
        return new OrderSummary(orderSave.getOrderId(), order.getOrderTotal(), orderSave.getOrderStatus().toString(), paymentConfirmation);
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return repo.findById(orderId);
    }

    @Override
    public Void deleteOrderById(String orderId) {
        repo.deleteById(orderId);
        log.debug("order delated");
        return null;
    }

    @Override
    public List<Order> getOrderHistory(String customerId) {
        return repo.findByOrderCustomerId(customerId);
    }

}
