package com.sergeystets.backoffice.etl.jdbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceFactory {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public DataSource getMysqlDataSource(String username,
                                         String password,
                                         String url) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
