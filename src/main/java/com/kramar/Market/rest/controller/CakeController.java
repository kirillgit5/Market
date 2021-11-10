package com.kramar.Market.rest.controller;

import com.kramar.Market.goods.CakeService;
import com.kramar.Market.goods.CakeServiceImpl;
import com.kramar.Market.orders.OrderEntity;
import com.kramar.Market.orders.OrderService;
import com.kramar.Market.purchases.PurchaseService;
import com.kramar.Market.rest.dto.*;
import com.kramar.Market.users.UserEntity;
import com.kramar.Market.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping("/v1/cakes")
public class CakeController {
    private Cakes cakesList = new Cakes();
    private final CakeService cakeService;
    private final UserService userService;
    private final OrderService orderService;
    private final PurchaseService purchaseService;

    @Autowired
    public CakeController(
            CakeService cakeService,
            UserService userService,
            OrderService orderService,
            PurchaseService purchaseService
    ) {
        this.cakeService = cakeService;
        this.userService = userService;
        this.orderService = orderService;
        this.purchaseService = purchaseService;

        List<Cake> tmp = new ArrayList<Cake>();
        cakesList.setCakesList(tmp);
    }

    @GetMapping(value = "cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return  cakeService.getCakes();
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeFullInfo cake(@PathVariable Long id) {
        return cakeService.getCakeFullInfo(id);
    }

    @PostMapping(path = "createCake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody @Valid Cake newCake) {
            cakesList.getCakesList().add(newCake);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderFullInfo> createOrder(@RequestBody @Valid OrderFullInfo newOrder) {
        UserEntity userEntity = userService.getUserBy(newOrder.getUserId());
        OrderEntity orderEntity = orderService.addOrder(newOrder.getOrder(),userEntity);
        for (Purchase purchase :newOrder.getPurchases()){
            purchaseService.addPurchase(orderEntity, cakeService.getCakeEntity(purchase.getCakeId()), purchase.getNumber());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
