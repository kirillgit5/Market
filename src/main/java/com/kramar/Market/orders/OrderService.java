package com.kramar.Market.orders;

import com.kramar.Market.users.UserEntity;
import com.kramar.Market.rest.dto.Order;

public interface OrderService {
    OrderEntity addOrder(Order order, UserEntity user);
}
