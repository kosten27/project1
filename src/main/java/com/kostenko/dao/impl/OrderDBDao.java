package com.kostenko.dao.impl;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.OrderDao;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDBDao implements OrderDao {

    @Autowired
    private DataSourceDB dataSource;

    public OrderDBDao(DataSourceDB dataSource) {
        this.dataSource = dataSource;
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean orderFound(long orderId) {
        boolean result = false;
        String sql = "SELECT * FROM ORDERS WHERE ORDERS.ID=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        String sqlDetail = "DELETE FROM PRODUCT_IN_ORDER WHERE ORDER_ID=?";
        String sqlOrder = "DELETE FROM ORDERS WHERE ID=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementDetail = connection.prepareStatement(sqlDetail);
             PreparedStatement statementOrder = connection.prepareStatement(sqlOrder)) {
            connection.setAutoCommit(false);
            statementDetail.setLong(1, orderId);
            statementOrder.setLong(1, orderId);
            statementDetail.executeUpdate();
            int countRows = statementOrder.executeUpdate();
            connection.commit();
            return (countRows > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementDelete = connection.prepareStatement("DELETE FROM PRODUCT_IN_ORDER WHERE ORDER_ID=?");
             PreparedStatement statementInsert = connection.prepareStatement("INSERT INTO PRODUCT_IN_ORDER(ORDER_ID,PRODUCT_ID) VALUES(?,?)")) {
            connection.setAutoCommit(false);
            statementDelete.setLong(1, order.getId());
            statementDelete.executeUpdate();
            for (Product product:order.getProducts()) {
                statementInsert.setLong(1, order.getId());
                statementInsert.setLong(2, product.getId());
                statementInsert.addBatch();
            }
            statementInsert.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order getOrder(long orderId) {
        String sqlOrder = "SELECT CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.ID WHERE ORDERS.ID=?";
        String sqlDetail = "SELECT PRODUCT_IN_ORDER.PRODUCT_ID, PRODUCT.NAME, PRODUCT.PRICE FROM PRODUCT_IN_ORDER LEFT JOIN PRODUCT ON PRODUCT_IN_ORDER.PRODUCT_ID = PRODUCT.ID WHERE ORDER_ID=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementOrder = connection.prepareStatement(sqlOrder);
             PreparedStatement statementDetail = connection.prepareStatement(sqlDetail)) {
            statementOrder.setLong(1, orderId);
            ResultSet resultSetOrder = statementOrder.executeQuery();
            if (resultSetOrder.next()) {
                long clientId = resultSetOrder.getLong("CLIENT_ID");
                String clientName = resultSetOrder.getString("NAME");
                String clientSurname = resultSetOrder.getString("SURNAME");
                int clientAge = resultSetOrder.getInt("AGE");
                String clientPhone = resultSetOrder.getString("PHONE");
                String clientEmail = resultSetOrder.getString("EMAIL");
                Client client = new Client(clientId, clientName, clientSurname, clientAge, clientEmail, clientPhone);

                statementDetail.setLong(1, orderId);
                ResultSet resultSetDetail = statementDetail.executeQuery();
                List<Product> products = new ArrayList<>();
                while (resultSetDetail.next()) {
                    long productId = resultSetDetail.getLong("PRODUCT_ID");
                    String productName = resultSetDetail.getString("NAME");
                    BigDecimal productPrice = resultSetDetail.getBigDecimal("PRICE");
                    products.add(new Product(productId, productName, productPrice));
                }
                resultSetDetail.close();
                resultSetOrder.close();
                return new Order(orderId, client, products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String sqlOrder = "SELECT ORDERS.ID, CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.ID";
        String sqlDetail = "SELECT PRODUCT_IN_ORDER.PRODUCT_ID, PRODUCT.NAME, PRODUCT.PRICE FROM PRODUCT_IN_ORDER LEFT JOIN PRODUCT ON PRODUCT_IN_ORDER.PRODUCT_ID = PRODUCT.ID WHERE ORDER_ID=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementOrder = connection.prepareStatement(sqlOrder);
             PreparedStatement statementDetail = connection.prepareStatement(sqlDetail)) {
            ResultSet resultSetOrder = statementOrder.executeQuery();
            while (resultSetOrder.next()) {
                long orderId = resultSetOrder.getLong("ID");
                long clientId = resultSetOrder.getLong("CLIENT_ID");
                String clientName = resultSetOrder.getString("NAME");
                String clientSurname = resultSetOrder.getString("SURNAME");
                int clientAge = resultSetOrder.getInt("AGE");
                String clientPhone = resultSetOrder.getString("PHONE");
                String clientEmail = resultSetOrder.getString("EMAIL");
                Client client = new Client(clientId, clientName, clientSurname, clientAge, clientEmail, clientPhone);

                statementDetail.setLong(1, orderId);
                ResultSet resultSetDetail = statementDetail.executeQuery();
                List<Product> products = new ArrayList<>();
                while (resultSetDetail.next()) {
                    long productId = resultSetDetail.getLong("PRODUCT_ID");
                    String productName = resultSetDetail.getString("NAME");
                    BigDecimal productPrice = resultSetDetail.getBigDecimal("PRICE");
                    products.add(new Product(productId, productName, productPrice));
                }
                resultSetDetail.close();
                orders.add(new Order(orderId, client, products));
            }
            resultSetOrder.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrders(long clientId) {
        List<Order> orders = new ArrayList<>();
        String sqlOrder = "SELECT ORDERS.ID, CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.ID WHERE ORDERS.CLIENT_ID=?";
        String sqlDetail = "SELECT PRODUCT_IN_ORDER.PRODUCT_ID, PRODUCT.NAME, PRODUCT.PRICE FROM PRODUCT_IN_ORDER LEFT JOIN PRODUCT ON PRODUCT_IN_ORDER.PRODUCT_ID = PRODUCT.ID WHERE ORDER_ID=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementOrder = connection.prepareStatement(sqlOrder);
             PreparedStatement statementDetail = connection.prepareStatement(sqlDetail)) {
            statementOrder.setLong(1, clientId);
            ResultSet resultSetOrder = statementOrder.executeQuery();
            while (resultSetOrder.next()) {
                long orderId = resultSetOrder.getLong("ID");
                String clientName = resultSetOrder.getString("NAME");
                String clientSurname = resultSetOrder.getString("SURNAME");
                int clientAge = resultSetOrder.getInt("AGE");
                String clientPhone = resultSetOrder.getString("PHONE");
                String clientEmail = resultSetOrder.getString("EMAIL");
                Client client = new Client(clientId, clientName, clientSurname, clientAge, clientEmail, clientPhone);

                statementDetail.setLong(1, orderId);
                ResultSet resultSetDetail = statementDetail.executeQuery();
                List<Product> products = new ArrayList<>();
                while (resultSetDetail.next()) {
                    long productId = resultSetDetail.getLong("PRODUCT_ID");
                    String productName = resultSetDetail.getString("NAME");
                    BigDecimal productPrice = resultSetDetail.getBigDecimal("PRICE");
                    products.add(new Product(productId, productName, productPrice));
                }
                resultSetDetail.close();
                orders.add(new Order(orderId, client, products));
            }
            resultSetOrder.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
