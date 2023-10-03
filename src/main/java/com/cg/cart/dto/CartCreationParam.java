package com.cg.cart.dto;

import com.cg.cartDetail.dto.CartDetailParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CartCreationParam {
    private List<CartDetailParam> items;
}