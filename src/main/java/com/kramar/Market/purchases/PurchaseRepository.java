package com.kramar.Market.purchases;

import org.springframework.data.jpa.repository.JpaRepository;

interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long> {
}
