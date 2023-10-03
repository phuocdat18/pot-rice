package com.cg.cart.dto;

import com.cg.cart.cartDetail.dto.CartItemResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CartResult {
    private Long id;
    private Long userId;
    private List<CartItemResult> items;
}