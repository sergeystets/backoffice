package com.sergeystets.backoffice.etl.jdbc;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Component
public class TransactionTemplateFactory {

    public TransactionTemplate getTransactionTemplate(DataSource dataSource) {
        return new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }
}
