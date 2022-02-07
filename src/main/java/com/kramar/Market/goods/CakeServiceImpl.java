package com.kramar.Market.goods;

import com.kramar.Market.exception.CakeNotFoundException;
import com.kramar.Market.rest.dto.Cake;
import com.kramar.Market.rest.dto.CakeFullInfo;
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

    @Override
    public CakeFullInfo getCakeFullInfo(Long id) {
        return  cakeRepository.findById(id)
                .map(entity -> {
                    CakeFullInfo cakeInfo = new  CakeFullInfo();
                    cakeInfo.setId(entity.getId());
                    cakeInfo.setCalories(entity.getCalories());
                    cakeInfo.setImage(entity.getImage());
                    cakeInfo.setManufacturerName(entity.getManufacturerName());
                    cakeInfo.setStorageConditions(entity.getStorageConditions());
                    cakeInfo.setPrice(entity.getPrice());
                    cakeInfo.setWeight(entity.getWeight());
                    cakeInfo.setName(entity.getName());

                    return  cakeInfo;
                })
                .orElseThrow(() -> new CakeNotFoundException("Cake not exist"));
    }

    @Override
    public void addCake(CakeFullInfo cake) {
        CakeEntity entity = new CakeEntity();
        entity.setPrice(cake.getPrice());
        entity.setCalories(cake.getCalories());
        entity.setImage(cake.getImage());
        entity.setWeight(cake.getWeight());
        entity.setManufacturerName(cake.getManufacturerName());
        entity.setStorageConditions(cake.getStorageConditions());
        entity.setName(cake.getName());

        cakeRepository.save(entity);
    }

    @Override
    public CakeEntity getCakeEntity(Long id) {
        return cakeRepository.findById(id).get();
    }
}
