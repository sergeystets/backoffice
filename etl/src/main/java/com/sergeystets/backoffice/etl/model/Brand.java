package com.sergeystets.backoffice.etl.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @CsvBindByName(column = "BRAND_ID", required = true)
    private long brandId;
    @CsvBindByName(column = "Name", required = true)
    private String name;
}
