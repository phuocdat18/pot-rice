package com.cg.product.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductCreationParam {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String unit;
    private Long categoryId;
}
