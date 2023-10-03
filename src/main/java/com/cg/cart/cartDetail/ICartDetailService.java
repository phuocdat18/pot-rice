package com.cg.cart.cartDetail;

import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    List<CartItemResult> findAll();

    CartItem findById(Long id);

    CartItem getById(Long id);

    void deleteById(Long id);

}