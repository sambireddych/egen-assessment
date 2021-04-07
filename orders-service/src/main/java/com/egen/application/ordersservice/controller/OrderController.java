package com.egen.application.ordersservice.controller;

import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.model.OrderSummary;
import com.egen.application.ordersservice.service.OrderManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;



@RestController
@RequestMapping("orders/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderManagerService orderservice;

    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody Order order) throws Exception {
        System.out.println("controller test: " + order.getBillingAddress());
        return new ResponseEntity<OrderSummary>(orderservice.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "orderId") String orderId) {
        return new ResponseEntity<>(orderservice.getOrderById(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable(name = "orderId") String orderId) {
        return new ResponseEntity<Void>(orderservice.deleteOrderById(orderId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order-history/{customerId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String customerId) {
        return ResponseEntity.ok(orderservice.getOrderHistory(customerId));
    }

}
