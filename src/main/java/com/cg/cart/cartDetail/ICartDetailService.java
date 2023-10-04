package com.cg.cart.cartDetail;

import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.model.CartItem;

import java.util.List;

public interface ICartDetailService {
    List<CartItemResult> findAll();

    CartItem findById(Long id);

    CartItem getById(Long cartItemId);

    void deleteById(Long id);

    List<CartItem> findAllByCartId(Long cartId);

    void changeQuantity(Long cartItemId, CartItemParam cartItemParam);
}