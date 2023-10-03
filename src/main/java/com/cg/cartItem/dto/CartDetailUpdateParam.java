package com.cg.cartItem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CartDetailUpdateParam {
    private Long productId;
    private Long quantity;
}