package com.kramar.Market.goods;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
     boolean existsByName(String name);
}
