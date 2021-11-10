package com.kramar.Market.orders;

import com.kramar.Market.rest.dto.Order;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity addOrder(Order order, UserEntity user) {
        OrderEntity entity = new OrderEntity();
        entity.setDeliverly(order.getDeliverly());
        entity.setDeliverlyAddress(order.getDeliverlyAddress());
        entity.setDeliverlyTime(LocalDateTime.now());
        entity.setPayment(order.getPayment());
        entity.setStatus(OrderStatus.New);
        entity.setUser(user);
        return orderRepository.saveAndFlush(entity);
    }
}
