package com.sergeystets.backoffice.etl.model;

import com.sergeystets.backoffice.etl.model.converter.LocalDateTimeConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quantity {

    @CsvBindByName(column = "BRAND_ID", required = true)
    private BigInteger brandId;
    @CsvBindByName(column = "QUANTITY", required = true)
    private BigInteger quantity;
    @CsvCustomBindByName(column = "TIME_RECEIVED", required = true, converter = LocalDateTimeConverter.class)
    private LocalDateTime timeReceived;
}
