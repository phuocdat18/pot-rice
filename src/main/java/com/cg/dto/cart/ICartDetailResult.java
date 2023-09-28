package com.cg.dto.cart;

import java.math.BigDecimal;

public interface ICartDetailResult {
    Long getId();

    String getTitle();

    String getUnit();

    BigDecimal getPrice();

    Long getQuantity();

    BigDecimal getAmount();
}
