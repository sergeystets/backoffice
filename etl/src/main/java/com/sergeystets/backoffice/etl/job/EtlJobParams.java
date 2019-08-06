package com.sergeystets.backoffice.etl.job;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Parameters(
        commandNames = EtlJobParams.COMMAND_NAME,
        commandDescription = "ETL job to migrate data from TSV files to MYSQL database"
)
public final class EtlJobParams {

    public static final String COMMAND_NAME = "etl";

    @Parameter(names = {"--brands", "-b"}, description = "Path to brands file in *.tsv format (e.g. /brands.tsv)", required = true)
    private String pathToBrandsFile;

    @Parameter(names = {"--quantity", "-q"}, description = "Path to quantity file in *.tsv format (e.g. /quantity.tsv)", required = true)
    private String pathToQuantityFile;

    @Parameter(names = {"--username", "-u"}, description = "MySQL username (e.g. user)", required = true)
    private String mysqlUsername;

    @Parameter(password = true, names = {"--password", "-p"}, description = "MySQL password (e.g. user-password)")
    private String mysqlPassword;

    @Parameter(names = {"--url"}, description = "MySQL JDBC URL (e.g. jdbc:mysql://localhost:3306/brands_db?useSSL=false)", required = true)
    private String mysqlUrl;

    @Parameter(names = {"--help", "-h"}, description = "Type this command to get command help", help = true)
    private boolean help = false;
}
