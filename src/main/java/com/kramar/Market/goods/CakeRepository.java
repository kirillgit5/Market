package com.kramar.Market.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;
import java.util.OptionalInt;

public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
     boolean existsByName(String name);

     @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
     @Lock(value = LockModeType.PESSIMISTIC_WRITE)
     Optional<CakeEntity> findFirstBy();
}
