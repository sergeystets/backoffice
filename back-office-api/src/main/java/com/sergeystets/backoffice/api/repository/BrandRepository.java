package com.sergeystets.backoffice.api.repository;

import com.sergeystets.backoffice.api.entity.Brand;
import com.sergeystets.backoffice.api.entity.projection.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, BigInteger> {

    @Query(value = "SELECT b.BRAND_ID AS brandId, b.NAME AS brandName, IFNULL(SUM(q.quantity), 0) AS quantity " +
            "FROM brand b LEFT JOIN quantity q ON b.BRAND_ID = q.BRAND_ID " +
            "GROUP BY b.BRAND_ID, b.NAME ORDER BY b.NAME", nativeQuery = true)
    List<Quantity> findQuantities();

}
