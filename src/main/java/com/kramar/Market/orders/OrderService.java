package com.kramar.Market.orders;

import com.kramar.Market.rest.dto.OrderFullInfo;
import com.kramar.Market.rest.dto.order.OrderAdapter;
import com.kramar.Market.rest.dto.order.OrderFullInfoUIAdapter;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.rest.dto.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    // MARK: - Admin methods
    List<OrderAdapter> getOrders();
    OrderFullInfoUIAdapter getOrderFullInfo(Long id);
    void deleteOrder(Long id);
    void updateOrder(Long id, OrderFullInfoUIAdapter order);
}