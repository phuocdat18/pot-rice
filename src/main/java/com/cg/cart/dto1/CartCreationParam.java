package com.cg.cart.dto1;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CartCreationParam {
    @NotNull(message = "total not null")
    private BigDecimal totalAmount;

}