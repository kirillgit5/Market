package com.kramar.Market.goods.dao;
import com.kramar.Market.goods.CakeEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CakeRowMapper implements RowMapper<CakeEntity> {
    @Override
    public CakeEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CakeEntity cake = new CakeEntity();

        cake.setName(resultSet.getString("name"));
        cake.setCalories(resultSet.getBigDecimal("calories"));
        cake.setWeight(resultSet.getBigDecimal("weight"));
        cake.setPrice(resultSet.getBigDecimal("price"));
        cake.setStorageConditions(resultSet.getString("storageConditions"));
        cake.setManufacturerName(resultSet.getString("manufacturerName"));
        cake.setImage(resultSet.getString("image"));

        return cake;
    }
}
