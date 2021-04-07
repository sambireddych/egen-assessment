package com.egen.application.ordersservice;


import com.egen.application.ordersservice.client.BillingPaymentClient;
import com.egen.application.ordersservice.client.BillingPaymentClientImpl;
import com.egen.application.ordersservice.client.ShippingClient;
import com.egen.application.ordersservice.client.ShippingClientImpl;
import com.egen.application.ordersservice.model.*;
import com.egen.application.ordersservice.model.enums.OrderStatus;
import com.egen.application.ordersservice.repo.OrderManagerRepo;
import com.egen.application.ordersservice.service.impl.OrderManagementServiceImpl;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
public class OrdersServiceImplTests {

    @Mock
    OrderManagerRepo orderManagerRepo;
    @Mock
    ShippingClientImpl shippingClient;
    @Mock
    BillingPaymentClientImpl billingPaymentClient;
    @Mock
    OrderManagementServiceImpl orderManagementService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void createOrders() throws Exception {

        Order orders =  mock(Order.class);
        orders.setOrderId("12ddsd");
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setOrderTotal(new BigDecimal("620.44"));
        orders.setOrderCreatedDate(new Date(new java.util.Date().getTime()));
        orders.setOrder_items(createOrderItems());
        orders.setBillingAddress(createAddress());
        orders.setShippingAddress(createAddress());
        orders.setPaymentDetails(savePaymentDetails());

        when(orderManagementService.createOrder(orders)).thenReturn(
                new OrderSummary("12ddsd",
                        new BigDecimal("620.44"), "PLACED",
                        null));
        verify(orderManagerRepo, times(1));
    }

    private List<OrderItems> createOrderItems(){
        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(new OrderItems("1","23","iphone",2,new BigDecimal("600"), 1));
        return orderItems;
    }


    private Address createAddress(){
        return new Address("145", "Kingston", "Port", "WV", "USA", "26330");
    }

    private List<PaymentDetails> savePaymentDetails(){
        List<PaymentDetails> details = new ArrayList<>();
        details.add(new PaymentDetails("VISA", "sambi","1234123412341234", 122,"12/24"));
        return details;
    }
}
