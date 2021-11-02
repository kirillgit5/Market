package com.kramar.Market.goods;

import com.kramar.Market.rest.dto.Cake;
import com.kramar.Market.rest.dto.Cakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeServiceImpl implements CakeService {
    private final CakeRepository cakeRepository;

    @Autowired
    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public Cakes getCakes() {
      List<CakeEntity> cakeEntityList = cakeRepository.findAll();
      List<Cake> cakeList = cakeEntityList.stream().map( cakeEntity -> {
          Cake cake = new Cake();
          cake.setId(cakeEntity.getId());
          cake.setCalories(cakeEntity.getCalories());
          cake.setName(cakeEntity.getName());
          cake.setImage(cakeEntity.getImage());
          cake.setPrice(cakeEntity.getPrice());
          cake.setWeight(cakeEntity.getWeight());
          return cake;
      }).collect(Collectors.toList());

      Cakes cakes = new Cakes();
      cakes.setCakesList(cakeList);

      return cakes;
    }
}
