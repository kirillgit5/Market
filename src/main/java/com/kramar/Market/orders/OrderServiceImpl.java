package com.kramar.Market.orders;

import com.kramar.Market.exception.CakeNotFoundException;
import com.kramar.Market.exception.OrderNotExistException;
import com.kramar.Market.goods.CakeRepository;
import com.kramar.Market.purchases.PurchaseEntity;
import com.kramar.Market.purchases.PurchaseRepository;
import com.kramar.Market.rest.controller.CakeController;
import com.kramar.Market.rest.dto.Order;
import com.kramar.Market.rest.dto.OrderFullInfo;
import com.kramar.Market.rest.dto.Purchase;
import com.kramar.Market.rest.dto.cake.CakeUIAdapter;
import com.kramar.Market.rest.dto.order.OrderAdapter;
import com.kramar.Market.rest.dto.order.OrderFullInfoUIAdapter;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.users.UserRepository;
import com.kramar.Market.users.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final CakeRepository cakeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            PurchaseRepository purchaseRepository,
                            UserRepository userRepository,
                            CakeRepository cakeRepository) {
        this.orderRepository = orderRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.cakeRepository = cakeRepository;
    }

    @Override
    public void addOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setDeliverly(order.getDeliverly());
        orderEntity.setDeliverlyAddress(order.getDeliverlyAddress());
        orderEntity.setDeliverlyTime(LocalDateTime.now());
        orderEntity.setPayment(order.getPayment());
        orderEntity.setStatus(OrderStatus.New);

        orderEntity.setPurchases(order.getPurchases().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setNumber(purchase.getNumber());
                    purchaseEntity.setCake(cakeRepository.findById(purchase.getId()).orElseThrow(RuntimeException::new));
                    return purchaseEntity;
                }).collect(Collectors.toList()));
        orderEntity.setUser(userRepository.findUserEntitiesByNumber(order.getUser().getPhoneNumber()));
        orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    public List<OrderAdapter> getOrders() {
        return orderRepository.findAll().stream().map(entity -> {
            return new OrderAdapter(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public OrderFullInfoUIAdapter getOrderFullInfo(Long id) {
        return orderRepository.findById(id).map(orderEntity -> {
            OrderFullInfoUIAdapter order = new OrderFullInfoUIAdapter();
            order.setOrderStatus(orderEntity.getStatus());
            order.setCustomerName(orderEntity.getUser().getName());
            order.setNumber(orderEntity.getUser().getNumber());
            order.setId(orderEntity.getId());
            order.setDeliverly(orderEntity.getDeliverly());
            order.setPayment(orderEntity.getPayment());
            order.setDeliverlyAddress(orderEntity.getDeliverlyAddress());
            order.setDeliverlyTime(order.getDeliverlyTime());

            List<CakeUIAdapter> cakes = orderEntity.getPurchases().stream().map(purchase -> {
                return cakeRepository.findById(purchase.getCake().getId()).map(cakeEntity -> {
                    CakeUIAdapter cake = new CakeUIAdapter();
                    cake.setName(cakeEntity.getName());
                    cake.setId(cakeEntity.getId());
                    cake.setPrice(cakeEntity.getPrice());
                    return cake;
                }).orElse(new CakeUIAdapter());
            }).collect(Collectors.toList());

            order.setCakes(cakes);
            return order;
        }).orElseThrow(() -> new OrderNotExistException("order not exist"));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(Long id, OrderFullInfoUIAdapter order) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow(() -> new OrderNotExistException("order not exist"));

        entity.setDeliverlyAddress(order.getDeliverlyAddress());
        entity.setDeliverlyTime(order.getDeliverlyTime());
        orderRepository.save(entity);

        return;
    }
}