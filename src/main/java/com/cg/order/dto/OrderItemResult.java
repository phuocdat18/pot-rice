package com.cg.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResult {
    private Long id;
    private String productTitle;
    private String unit;
    private BigDecimal price;
    private Long quantity;
//    private BigDecimal amount;
}
