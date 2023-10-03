package com.cg.cartDetail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CartDetailParam {
    private Long productId;
    private Long quantity;
}
