package com.kostenko.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataSourceDB {

    private static final String DEFAULT_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private final String URL;
//    private static final String URL = "jdbc:h2:mem:LuxoftShop;DB_CLOSE_DELAY=-1";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public DataSourceDB() {
        this.URL = DEFAULT_URL;
    }

    public DataSourceDB(String url) {
        this.URL = url;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
