package com.cg.cart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CartUpdateParam {
    private Long productId;
    private Long quantity;
}
