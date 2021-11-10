package com.kramar.Market.purchases;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.orders.OrderEntity;

public interface PurchaseService {
    void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer number);
}
