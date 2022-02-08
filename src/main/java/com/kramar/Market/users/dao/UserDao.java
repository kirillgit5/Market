package com.kramar.Market.users.dao;

import com.kramar.Market.users.UserEntity;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDao {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserEntity getUserBy(String number) {
        UserEntity userEntity = new UserEntity();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM userentity WHERE number=" + number;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                userEntity.setNumber(resultSet.getString("number"));
                userEntity.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
