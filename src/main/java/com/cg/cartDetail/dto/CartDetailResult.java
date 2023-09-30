package com.cg.cartDetail.dto;

import com.cg.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Getter
@Setter
@Accessors(chain = true)
public class CartDetailResult {
    private Long id;
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
}
