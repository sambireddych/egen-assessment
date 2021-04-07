package com.egen.application.ordersservice.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "Order_Items")
@AllArgsConstructor
public class OrderItems implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_item_id")
    private String orderItemId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_item_name")
    @NonNull
    private String orderItemName;

    @Column(name = "order_item_quantity")
    @NotNull
    private int orderItemQuantity;

    @Column(name = "order_item_unit_price")
    @NotNull
    private BigDecimal orderItemUnitPrice;

    @Version
    private long version;

    public OrderItems() {
    }
}
