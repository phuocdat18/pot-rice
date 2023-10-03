package com.cg.cart1.dto;

import com.cg.cartItem.dto.CartDetailUpdateParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CartUpdateParam {
    private List<CartDetailUpdateParam> items;
}
