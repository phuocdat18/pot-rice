package com.cg.cartDetail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class CartDetailDTO {
    private Long id;
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;

    public CartDetailDTO (Long id, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount) {
        this.id = id;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }
}
