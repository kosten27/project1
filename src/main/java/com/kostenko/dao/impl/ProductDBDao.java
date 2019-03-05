package com.kostenko.dao.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Product;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDao implements ProductDao {
    private final String URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private final String USER = "test";
    private final String PASSWORD = "test";

    public ProductDBDao() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "PRODUCT", null);
            if (!tables.next()) {
                String sql = "CREATE TABLE PRODUCT (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), PRICE DECIMAL(15,2))";
                statement.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveProduct(Product product) {
        String sql = "INSERT INTO PRODUCT(NAME,PRICE) VALUES(?,?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            int countRows = statement.executeUpdate();
            if (countRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean productFound(long productId) {
        boolean result = false;
        String sql = "SELECT * FROM PRODUCT WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE PRODUCT SET NAME=?, PRICE=? WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(long productId) {
        String sql = "DELETE FROM PRODUCT WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product getProduct(long id) {
        String sql = "SELECT * FROM PRODUCT WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long productId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                BigDecimal price = resultSet.getBigDecimal(3);
                resultSet.close();
                return new Product(id, name, price);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PRODUCT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal price = resultSet.getBigDecimal("PRICE");
                products.add(new Product(id, name, price));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
