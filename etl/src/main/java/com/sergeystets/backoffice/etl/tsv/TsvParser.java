package com.sergeystets.backoffice.etl.tsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class TsvParser {

    public <T> List<T> parse(Path path, Class<T> clazz) throws Exception {
        MappingStrategy<T> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(clazz);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                .withMappingStrategy(ms)
                .withIgnoreQuotations(true)
                .withSeparator('\t')
                .withFilter(StringUtils::isNoneBlank)
                .build();

        return cb.parse();
    }
}
