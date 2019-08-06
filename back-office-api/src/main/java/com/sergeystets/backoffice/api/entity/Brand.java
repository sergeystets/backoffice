package com.sergeystets.backoffice.api.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @Column(name = "BRAND_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255)")
    private String name;
}
