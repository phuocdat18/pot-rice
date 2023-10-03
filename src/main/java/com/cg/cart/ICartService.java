package com.cg.cart;

import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.dto.CartResult;
import com.cg.model.Cart;

import java.util.List;

public interface ICartService {
    Cart findById(Long id);

    CartResult getById(Long id);

    List<CartResult> findAll();

    List<?> findAllByUserId(Long userId);

    void addCartItem(Long cartId, CartItemParam cartItemParam, Long userId);

    CartResult newCart(Long userId);
}
