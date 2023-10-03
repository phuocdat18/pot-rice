package com.cg.cartItem.dto;

import com.cg.cart1.dto.CartResult;
import com.cg.model.Cart;
import com.cg.product.dto.ProductResult;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartDetailResult {
    private Long id;
    private String title;
    private BigDecimal price;
    private ProductResult productResult;
    private Long quantity;
    private Long cartId;
    private CartResult cartResult;
}
