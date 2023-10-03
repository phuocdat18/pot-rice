package com.cg.cart.dto;

import com.cg.cartDetail.dto.CartDetailUpdateParam;
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
