package com.cg.cartItem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CartDetailParam {
    private Long productId;
    private String quantity;
}
