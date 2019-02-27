package com.kostenko.dao.impl;


import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao{

    public static final String URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    public static final String USER = "test";
    public static final String PASSWORD = "test";

    public ClientDBDao() {
//        try {
//                Class.forName("org.h2.Driver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//        }

//        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            Statement statement = connection.createStatement();) {
//            statement.execute("CREATE TABLE CLIENT(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50));");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public boolean saveClient(Client client) {
//        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            Statement statement = connection.createStatement();) {
//
//            statement.execute("INSERT INTO CLIENT(NAME,SURNAME,AGE,PHONE,EMAIL) VALUES('" + client.getName() + "','" + client.getSurname() + "'," + client.getAge() + ",'" + client.getPhone() + "','" + client.getEmail() + "'");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME,SURNAME,AGE,PHONE,EMAIL) VALUES(?,?,?,?,?)")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(long clientId) {
        return false;
    }

    @Override
    public Client getClient(long clientId) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID = ?")) {
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
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String sername = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                clients.add(new Client(id, name, sername, age, email, phone));
            };
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public boolean phoneUsed(String phone) {
        return false;
    }
}
