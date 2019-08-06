package com.sergeystets.backoffice.api.entity.projection;

import java.math.BigInteger;

public interface Quantity {

    BigInteger getBrandId();

    String getBrandName();

    BigInteger getQuantity();
}
