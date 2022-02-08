package com.kramar.Market.users.dao;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.users.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserEntity user = new UserEntity();
        user.setNumber(resultSet.getString("number"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}
