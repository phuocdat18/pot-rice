package com.cg.cart.cartDetail.dto;

import com.cg.avatar.dto.ProductAvatarDTO;
import com.cg.cart.dto.CartResult;
import com.cg.product.dto.ProductResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CartItemResult {
    private Long id;
    private ProductResult productResult;
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private Long cartId;
    private CartResult cartResult;
    private ProductAvatarDTO avatar;
}
