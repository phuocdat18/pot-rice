package com.cg.cartDetail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Getter
@Setter
@Accessors(chain = true)
public class CartDetailCreationParam {
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
}
