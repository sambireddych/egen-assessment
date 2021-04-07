package com.egen.application.ordersservice.model;


import com.egen.application.ordersservice.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_customer_id")
    @NotNull()
    private String orderCustomerId;

    @Column(name = "order_date")
    private Date orderCreatedDate;

    @Column(name = "Order_modified_date")
    private Date orderModifiedDate;

    @Column(name = "order_total")
    private BigDecimal orderTotal;

    @Column(name = "order_subtotal")
    private BigDecimal orderSubtotal;

    @Column(name = "order_tax")
    private BigDecimal orderTax;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_shipped_date")
    private Date orderShippedDate;

    @Version
    private long version;

    @Transient
    private Address billingAddress;

    @Transient
    private Address shippingAddress;

    @Transient
    @JsonInclude
    private BigDecimal orderShippingCharges;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItems> order_items;

    @Transient
    private List<PaymentDetails> paymentDetails;


    protected Order(OrderStatus pending, Date date) {
        this.orderStatus = OrderStatus.PENDING;
        this.orderCreatedDate = new Date(new java.util.Date().getTime());
    }
}
