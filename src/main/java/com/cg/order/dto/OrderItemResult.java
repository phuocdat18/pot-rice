package com.cg.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResult {
    private Long id;
    private String productTitle;
    private String unit;
    private BigDecimal price;
    @Size(min = 1, message = "")
    private Long quantity;
}
