package com.cg.cart.cartDetail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class CartItemParam {
    @NotNull
    private Long productId;
    @Size(min = 1, message = "")
    private Long quantity;

    private Long userId;
}