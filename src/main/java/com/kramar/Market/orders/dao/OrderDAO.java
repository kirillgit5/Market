package com.kramar.Market.orders.dao;

import com.kramar.Market.orders.OrderStatus;
import com.kramar.Market.rest.dto.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

@Component
public class OrderDAO {
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

    public void addOrder(Order order, Long userId) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO orderfullinfo(user_id, deliverly, payment, status, deliverly_address, deliverly_time) VALUES ('"+
                    userId+"','"+
                    order.getDeliverly().ordinal()+"','" +
                    order.getPayment().ordinal()+"','"+
                    OrderStatus.New.ordinal()+"','"+
                    order.getDeliverlyAddress()+"','"+
                    LocalDateTime.now()+
                    "')";

            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}