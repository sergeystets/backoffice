package com.sergeystets.backoffice.api.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "quantity")
public class Quantity {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "BRAND_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger brandId;

    @Column(name = "QUANTITY", nullable = false)
    private BigInteger quantity;

    @Column(name = "TIME_RECEIVED", nullable = false)
    private LocalDateTime timeReceived;
}
