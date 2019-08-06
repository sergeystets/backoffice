package com.sergeystets.backoffice.api.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class QuantityDto {

    private final BigInteger brandId;
    private final String brandName;
    private final BigInteger quantity;
}
