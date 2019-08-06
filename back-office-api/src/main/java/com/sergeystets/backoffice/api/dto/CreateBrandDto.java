package com.sergeystets.backoffice.api.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class CreateBrandDto {

    @NotNull
    @Length(min = 1, max = 255)
    private final String name;
}
