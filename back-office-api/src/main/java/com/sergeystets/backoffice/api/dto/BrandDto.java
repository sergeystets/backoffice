package com.sergeystets.backoffice.api.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class BrandDto {

    private final BigInteger id;
    private final String name;
}
