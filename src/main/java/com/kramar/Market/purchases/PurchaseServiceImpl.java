package com.kramar.Market.purchases;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.orders.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer number) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setNumber(number);
        purchase.setOrder(orderEntity);
        purchase.setCake(cakeEntity);
        purchaseRepository.saveAndFlush(purchase);
    }
}
