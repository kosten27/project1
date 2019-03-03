package com.kostenko.dao.impl;

import com.kostenko.dao.OrderDao;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;

import java.sql.*;
import java.util.List;

public class OrderDBDao implements OrderDao {
    private final String URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private final String USER = "test";
    private final String PASSWORD = "test";

    public OrderDBDao() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "ORDERS", null);
            if (!tables.next()) {
                String sql = "CREATE TABLE ORDERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, CLIENT_ID BIGINT, FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(ID));" +
                        "CREATE TABLE PRODUCT_IN_ORDER (ORDER_ID BIGINT, PRODUCT_ID BIGINT, FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID), FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID))";
                statement.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveOrder(Order order) {
        String sql = "INSERT INTO ORDERS(CLIENT_ID) VALUES(?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setLong(1, order.getClient().getId());
            int countRows = statement.executeUpdate();
            if (countRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getLong("ID"));
                    String sqlProduct = "INSERT INTO PRODUCT_IN_ORDER(ORDER_ID,PRODUCT_ID) VALUES(?,?)";
                    try (PreparedStatement statementProduct = connection.prepareStatement(sqlProduct)) {
                        statementProduct.setLong(1, order.getId());
                        for (Product product:order.getProducts()) {
                            statementProduct.setLong(2, product.getId());
                            statementProduct.addBatch();
                        }
                        statementProduct.executeBatch();
                    }
                }
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        String sql = "DELETE FROM PRODUCT_IN_ORDER WHERE ORDER_ID=?;DELETE FROM ORDERS WHERE ID=?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            statement.setLong(2, orderId);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long clientId, long orderId) {
        return false;
    }

    @Override
    public Order getOrder(long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public List<Order> getOrders(long clientId) {
        return null;
    }
}
