package com.sergeystets.backoffice.api.repository;

import com.sergeystets.backoffice.api.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, BigInteger> {
}
