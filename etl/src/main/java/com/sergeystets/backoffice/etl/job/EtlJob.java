package com.sergeystets.backoffice.etl.job;

import com.sergeystets.backoffice.etl.jdbc.DataSourceFactory;
import com.sergeystets.backoffice.etl.jdbc.JdbcTemplateFactory;
import com.sergeystets.backoffice.etl.jdbc.TransactionTemplateFactory;
import com.sergeystets.backoffice.etl.model.Brand;
import com.sergeystets.backoffice.etl.model.Quantity;
import com.sergeystets.backoffice.etl.tsv.TsvParser;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

@Component
@AllArgsConstructor
public class EtlJob {

    private final TsvParser tsvParser;
    private final DataSourceFactory dataSourceFactory;
    private final TransactionTemplateFactory txFactory;
    private final JdbcTemplateFactory jdbcTemplateFactory;

    public void run(EtlJobParams params) throws Exception {
        // extract data from tsv files
        List<Brand> brands = tsvParser.parse(new File(params.getPathToBrandsFile()).toPath(), Brand.class);
        List<Quantity> quantities = tsvParser.parse(new File(params.getPathToQuantityFile()).toPath(), Quantity.class);

        DataSource dataSource = dataSourceFactory.getMysqlDataSource(
                params.getMysqlUsername(),
                params.getMysqlPassword(),
                params.getMysqlUrl());
        NamedParameterJdbcTemplate jdbc = jdbcTemplateFactory.getJdbcTemplate(dataSource);
        TransactionTemplate transaction = txFactory.getTransactionTemplate(dataSource);

        // load data into a database inside a transaction (to make sure 'all or none' were loaded)
        transaction.execute((status) -> {
            runDdl(jdbc);
            runDml(brands, quantities, jdbc);
            return null;
        });
    }

    private void runDdl(NamedParameterJdbcTemplate jdbc) {
        // drop 'brands' table
        jdbc.getJdbcTemplate().execute("DROP TABLE IF EXISTS quantity");

        // drop 'quantity' table
        jdbc.getJdbcTemplate().execute("DROP TABLE IF EXISTS brand");

        // create 'brands' table
        jdbc.getJdbcTemplate().execute("CREATE TABLE brand (" +
                "BRAND_ID BIGINT NOT NULL AUTO_INCREMENT, " +
                "NAME VARCHAR(255) NOT NULL," +
                "PRIMARY KEY (BRAND_ID)" +
                ")");

        // create 'quantities' table
        jdbc.getJdbcTemplate().execute("CREATE TABLE quantity (" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, " +
                "QUANTITY BIGINT NOT NULL," +
                "TIME_RECEIVED DATETIME NOT NULL," +
                "BRAND_ID BIGINT NOT NULL," +
                "PRIMARY KEY (ID), " +
                "FOREIGN KEY (BRAND_ID) REFERENCES brand(BRAND_ID) ON DELETE CASCADE" +
                ")");
    }

    private void runDml(List<Brand> brands,
                        List<Quantity> quantities,
                        NamedParameterJdbcTemplate jdbc) {
        // insert new brands
        jdbc.batchUpdate("INSERT INTO brand VALUES(:brandId, :name)",
                SqlParameterSourceUtils.createBatch(brands));

        // insert new quantities
        jdbc.batchUpdate("INSERT INTO quantity (QUANTITY, TIME_RECEIVED, BRAND_ID) VALUES(:quantity, :timeReceived, :brandId)",
                SqlParameterSourceUtils.createBatch(quantities));

    }
}
