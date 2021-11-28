package com.kramar.Market.orders;

import com.kramar.Market.rest.dto.OrderFullInfo;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.rest.dto.Order;

public interface OrderService {
    void addOrder(Order order);
}
