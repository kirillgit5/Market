package com.kramar.Market.orders;

import com.kramar.Market.exception.CakeNotFoundException;
import com.kramar.Market.goods.CakeRepository;
import com.kramar.Market.orders.dao.OrderDAO;
import com.kramar.Market.purchases.PurchaseEntity;
import com.kramar.Market.purchases.PurchaseRepository;
import com.kramar.Market.rest.controller.CakeController;
import com.kramar.Market.rest.dto.Order;
import com.kramar.Market.rest.dto.OrderFullInfo;
import com.kramar.Market.rest.dto.Purchase;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.users.UserRepository;
import com.kramar.Market.users.UserService;
import com.kramar.Market.users.dao.UserDAOTemplate;
import com.kramar.Market.users.dao.UserDao;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final PurchaseRepository purchaseRepository;
    private final CakeRepository cakeRepository;
    private final OrderDAO orderDao;
    private final UserDAOTemplate userDAOTemplate;

    @Autowired
    public OrderServiceImpl(PurchaseRepository purchaseRepository,
                            CakeRepository cakeRepository,
                            OrderDAO orderDao,
                            UserDAOTemplate userDAOTemplate) {
        this.purchaseRepository = purchaseRepository;
        this.cakeRepository = cakeRepository;
        this.orderDao = orderDao;
        this.userDAOTemplate = userDAOTemplate;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order, userDAOTemplate.getUserBy(order.getUser().getPhoneNumber()).getId());
    }
}