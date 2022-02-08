package com.kramar.Market.users.dao;

import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.goods.dao.CakeRowMapper;
import com.kramar.Market.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAOTemplate {
    private final JdbcTemplate template;

    @Autowired
    public UserDAOTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public UserEntity getUserBy(String number) {
        UserRowMapper mapper = new UserRowMapper();
        UserEntity userEntity = template.query("SELECT * FROM userentity WHERE number= ?", mapper, number).stream().findFirst().orElse(null);
        return  userEntity;
    }
}