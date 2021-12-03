package com.kramar.Market.rest.dto.order;

import com.kramar.Market.orders.Deliverly;
import com.kramar.Market.orders.OrderEntity;
import com.kramar.Market.orders.OrderStatus;
import com.kramar.Market.orders.Payment;
import lombok.Data;

@Data
public class OrderAdapter {
    private String customerName;
    private String number;
    private Deliverly deliverly;
    private Payment payment;
    private OrderStatus orderStatus;
    private Long id;

    public OrderAdapter(OrderEntity orderEntity) {
        this.customerName = orderEntity.getUser().getName();
        this.number = orderEntity.getUser().getNumber();
        this.deliverly = orderEntity.getDeliverly();
        this.payment = orderEntity.getPayment();
        this.orderStatus = orderEntity.getStatus();
        this.id = orderEntity.getId();
    }
}
