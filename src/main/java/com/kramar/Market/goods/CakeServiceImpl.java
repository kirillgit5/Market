package com.kramar.Market.goods;

import com.kramar.Market.exception.CakeNotFoundException;
import com.kramar.Market.goods.dao.CakeDAO;
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
    private final CakeDAO cakeDAO;

    @Autowired
    public CakeServiceImpl(CakeRepository cakeRepository, CakeDAO cakeDAO) {
        this.cakeRepository = cakeRepository;
        this.cakeDAO = cakeDAO;
    }

    @Override
    public Cakes getCakes() {
        List<CakeEntity> cakeList = cakeDAO.getCakes();
        Cakes cakes = new Cakes();

        List<Cake> cakesAdapt = cakeList.stream().map(cakeEntity -> {
            Cake cake = new Cake();
            cake.setWeight(cakeEntity.getWeight());
            cake.setCalories(cakeEntity.getWeight());
            cake.setPrice(cakeEntity.getPrice());
            cake.setImage(cakeEntity.getImage());
            cake.setName(cakeEntity.getName());
            cake.setImage(cakeEntity.getImage());

            return cake;
        }).collect(Collectors.toList());


        cakes.setCakesList(cakesAdapt);
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
        cakeDAO.addCake(cake);
    }

    @Override
    public CakeEntity getCakeEntity(Long id) {
        return cakeDAO.getCake(id);
    }
}
