package com.kramar.Market.goods.dao;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.rest.dto.CakeFullInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CakeDAOTemplate {
    private final JdbcTemplate template;

    @Autowired
    public CakeDAOTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public CakeEntity getCake(Long id) {
        CakeRowMapper mapper = new CakeRowMapper();
       CakeEntity cakeEntity = template.query("SELECT * FROM cake WHERE id= ?", mapper, id).stream().findFirst().orElse(null);
       return  cakeEntity;
    }

    public List<CakeEntity> getCakes() {
        CakeRowMapper mapper = new CakeRowMapper();
        List<CakeEntity> cakes = template.query("SELECT * FROM cake", mapper);
        return cakes;
    }

    public void deleteCake(Long id) {
        String deleteQuery = "delete from cake where id = ?";
        template.update(deleteQuery, id);
    }

    public void addCake(CakeFullInfo cakeFullInfo) {
        template.update("INSERT INTO cake(name, calories, weight, price, storage_conditions, manufacturer_name, image) VALUES(?, ?, ?, ?, ?, ?, ?)",
                cakeFullInfo.getName(), cakeFullInfo.getCalories(), cakeFullInfo.getWeight(), cakeFullInfo.getPrice(),
                cakeFullInfo.getStorageConditions(), cakeFullInfo.getManufacturerName(), cakeFullInfo.getImage());
    }
}
