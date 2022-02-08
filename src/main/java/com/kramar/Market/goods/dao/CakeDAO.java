package com.kramar.Market.goods.dao;
import com.kramar.Market.goods.CakeEntity;
import com.kramar.Market.rest.dto.Cake;
import com.kramar.Market.rest.dto.CakeFullInfo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CakeDAO {
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

    public CakeEntity getCake(Long id) {
        CakeEntity cake = new CakeEntity();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM cake WHERE id="+id;
            ResultSet resultSet= statement.executeQuery(sql);

            while (resultSet.next()) {
                cake.setName(resultSet.getString("name"));
                cake.setCalories(resultSet.getBigDecimal("calories"));
                cake.setWeight(resultSet.getBigDecimal("weight"));
                cake.setPrice(resultSet.getBigDecimal("price"));
                cake.setStorageConditions(resultSet.getString("storageConditions"));
                cake.setManufacturerName(resultSet.getString("manufacturerName"));
                cake.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cake;
    }

    public List<CakeEntity> getCakes() {
        ArrayList<CakeEntity> cakes = new ArrayList<CakeEntity>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM cake";
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                CakeEntity cake = new CakeEntity();

                cake.setName(resultSet.getString("name"));
                cake.setCalories(resultSet.getBigDecimal("calories"));
                cake.setWeight(resultSet.getBigDecimal("weight"));
                cake.setPrice(resultSet.getBigDecimal("price"));
                cake.setStorageConditions(resultSet.getString("storage_conditions"));
                cake.setManufacturerName(resultSet.getString("manufacturer_name"));
                cake.setImage(resultSet.getString("image"));

                cakes.add(cake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  cakes;
    }

    public void deleteCake(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM cake WHERE id="+id;
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void  addCake(CakeFullInfo cakeFullInfo) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO cake(name, calories, weight, price, storage_conditions, manufacturer_name, image) VALUES('" +
                    cakeFullInfo.getName() + "','" +
                    cakeFullInfo.getCalories() +"','" +
                    cakeFullInfo.getWeight()+"','"+
                    cakeFullInfo.getPrice()+"','"+
                    cakeFullInfo.getStorageConditions()+"','" +
                    cakeFullInfo.getManufacturerName()+"','"+
                    cakeFullInfo.getImage()+
                    "')";

            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}