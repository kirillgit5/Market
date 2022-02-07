package com.kramar.Market.rest.dto.order;

import com.kramar.Market.orders.Deliverly;
import com.kramar.Market.orders.OrderStatus;
import com.kramar.Market.orders.Payment;
import com.kramar.Market.rest.dto.cake.CakeUIAdapter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderFullInfoUIAdapter {
    private String customerName;
    private String number;
    private Deliverly deliverly;
    private Payment payment;
    private OrderStatus orderStatus;
    private Long id;
    private String deliverlyAddress;
    private LocalDateTime deliverlyTime;
    private List<CakeUIAdapter> cakes;
}
