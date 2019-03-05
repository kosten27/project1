package com.kostenko.dao.impl;


import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao{

    private static final String URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public ClientDBDao() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "CLIENT", null);
            if (!resultSet.next()) {
                String sql = "CREATE TABLE CLIENT(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50));";
                statement.execute(sql);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean saveClient(Client client) {
        String sql = "INSERT INTO CLIENT(NAME,SURNAME,AGE,PHONE,EMAIL) VALUES(?,?,?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            int countRows = statement.executeUpdate();
            if (countRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getLong(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean clientFound(long clientId) {
        boolean result = false;
        String sql = "SELECT * FROM CLIENT WHERE ID = ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateClient(Client client) {
        String sql = "UPDATE CLIENT SET NAME=?,SURNAME=?,AGE=?,PHONE=?,EMAIL=? WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.setLong(6, client.getId());
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteClient(long clientId) {
        String sql = "DELETE FROM CLIENT WHERE ID=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client getClient(long clientId) {
        String sql = "SELECT * FROM CLIENT WHERE ID = ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String sername = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                resultSet.close();
                return new Client(id, name, sername, age, email, phone);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM CLIENT";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                clients.add(new Client(id, name, surname, age, email, phone));
            };
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public boolean phoneUsed(String phone) {
        String sql = "SELECT ID FROM CLIENT WHERE PHONE=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
