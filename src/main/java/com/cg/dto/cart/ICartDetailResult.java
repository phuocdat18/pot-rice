package com.cg.dto.cart;

import com.cg.dto.product.ProductDTO;

import java.math.BigDecimal;

public interface ICartDetailResult {
    Long getId();

    String getTitle();

    String getUnit();

    BigDecimal getPrice();

    Long getQuantity();

    BigDecimal getAmount();
}
